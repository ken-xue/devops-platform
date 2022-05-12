package io.kenxue.cicd.application.pipeline.pipeline.command;

import com.alibaba.fastjson.JSON;
import io.kenxue.cicd.application.common.event.EventBusI;
import io.kenxue.cicd.application.pipeline.pipeline.manager.PipelineNodeManager;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineExecuteCmd;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.event.PipelineNodeRefreshEvent;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.cicd.domain.factory.pipeline.NodeExecuteLoggerFactory;
import io.kenxue.cicd.domain.factory.pipeline.PipelineExecuteLoggerFactory;
import io.kenxue.cicd.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import io.kenxue.cicd.domain.repository.pipeline.PipelineNodeInfoRepository;
import io.kenxue.cicd.domain.repository.pipeline.PipelineRepository;
import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import io.kenxue.cicd.sharedataboject.pipeline.enums.NodeEnum;
import io.kenxue.cicd.sharedataboject.pipeline.enums.NodeExecuteStatus;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Graph;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 流水线
 *
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@Service
public class PipelineExecuteCmdExe implements DisposableBean {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 20L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    //当前正在执行的节点 k=记录uuid+"&"+节点uuid
    private static volatile ConcurrentHashMap<String, NodeLogger> executingNodeMap = new ConcurrentHashMap<>(2 << 4);

    @Resource
    private PipelineExecuteLoggerRepository loggerRepository;
    @Resource
    private PipelineRepository pipelineRepository;
    @Resource
    private PipelineNodeInfoRepository nodeInfoRepository;
    @Resource
    private PipelineNodeManager pipelineNodeManager;
    @Resource
    private EventBusI eventBus;

    /**
     * 流水线执行入口
     *
     * @param cmd
     * @return
     */
    public Response execute(PipelineExecuteCmd cmd) {

        Pipeline pipeline = pipelineRepository.getById(cmd.getId());

        PipelineExecuteContext context = buildContext(pipeline);

        context.setLogger(logger(pipeline));

        executor.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            execute(context, context.getStart());
        });

        return SingleResponse.of(context.getLogger());
    }

    /**
     * 生成执行记录
     *
     * @param pipeline
     */
    private PipelineExecuteLogger logger(Pipeline pipeline) {
        PipelineExecuteLogger logger = PipelineExecuteLoggerFactory.getPipelineExecuteLogger();
        logger.create(UserThreadContext.get());
        logger.setPipelineUuid(pipeline.getUuid());
        logger.setExecuteStartTime(new Date());
        logger.setGraphContent(JSON.toJSONString(pipeline.getGraph()));
        loggerRepository.create(logger);
        return logger;
    }

    /**
     * 构建上下文
     *
     * @param pipeline
     */
    private PipelineExecuteContext buildContext(Pipeline pipeline) {

        PipelineExecuteContext context = new PipelineExecuteContext();

        Graph graph = pipeline.getGraph();
        List<Nodes> nodes = graph.getNodes();
        context.setNodes(nodes);
        context.setGraph(graph);

        for (Nodes node : nodes) {
            //暂无需理会开始结束节点
            if (NodeEnum.START.getName().equals(node.getName()) || NodeEnum.END.getName().equals(node.getName())) {
                continue;
            }
            PipelineNodeInfo nodeInfo = nodeInfoRepository.getByNodeId(node.getId());
            if (Objects.isNull(nodeInfo)) {
                log.error("node : {},config node info data is null", node);
            } else {
                context.setAttributes(node.getName(), nodeInfo);
            }
        }
        //获取开始节点
        context.setStart(nodes.stream().filter(node -> NodeEnum.START.toString().equals(node.getName())).findFirst().get());
        //映射每个节点对应的输入和输出节点
        for (Nodes n : nodes) {
            //当前节点n作为target
            n.getPoints().getTargets().forEach(uuid -> context.getTargetMap().put(uuid.replace("target-", ""), n));
            //当前节点n作为source
            n.getPoints().getSources().forEach(uuid -> context.getSourceMap().put(uuid.replace("source-", ""), n));
        }
        //获取执行路径
        List<String> edges = graph.getEdges();
        for (String edge : edges) {
            String[] lines = edge.split("&&");
            String source = lines[0].replace("source-", "");
            String target = lines[1].replace("target-", "");
            //target
            List<String> targetLineMapOrDefault = context.getTargetLineMap().getOrDefault(source, new LinkedList<>());
            targetLineMapOrDefault.add(target);
            context.getTargetLineMap().put(source, targetLineMapOrDefault);
            //source
            List<String> sourceLineMapOrDefault = context.getSourceLineMap().getOrDefault(target, new LinkedList<>());
            sourceLineMapOrDefault.add(source);
            context.getSourceLineMap().put(target, sourceLineMapOrDefault);
        }

        return context;
    }


    /**
     * 执行流水线
     *
     * @param context
     * @param node
     */
    public void execute(PipelineExecuteContext context, Nodes node) {

        //判断是否可执行
        if (!executable(context, node)) {
            return;
        }

        try {
            log.info("执行的节点：{}", node.getName());
            //执行结果
            Result result = new DefaultResult();
            //变更状态//进行中
            node.refreshStatus(NodeExecuteStatus.LOADING);
            //加入缓存
            NodeLogger logger = NodeExecuteLoggerFactory.getNodeExecuteLogger().setExecuteStartTime(new Date());
            context.setAttributes(node.getName()+"logger",logger);
//            executingNodeMap.put(String.format("%s&%s", context.getLogger().getUuid(), node.getId()), node);
            executingNodeMap.put(String.format("%s&%s", context.getLogger().getUuid(), node.getId()), logger);
            //发送事件
            eventBus.publish(new PipelineNodeRefreshEvent(context.getLogger().getUuid(), node, context.getSourceLineMap(), NodeExecuteStatus.LOADING));
            //执行节点
            context.setAttributes(node.getName() + "logger-uuid", context.getLogger().getUuid());
            context.setAttributes(node.getName() + "node-uuid", node.getId());
            Result ret = pipelineNodeManager.get(node.getName()).execute(context);
            result.add(node.getName(), ret);
            //执行成功
            node.refreshStatus(NodeExecuteStatus.SUCCESS);
            //获取下一个执行的路线
            List<String> sources = node.getPoints().getSources();
            sources.forEach(sce -> {
                String next = sce.replace("source-", "");
                List<String> list = context.getTargetLineMap().getOrDefault(next, Collections.emptyList());
                list.forEach(v -> executor.submit(() -> execute(context, context.getTargetMap().get(v))));
            });

        } catch (Exception e) {
            //执行失败
            node.refreshStatus(NodeExecuteStatus.FAILED);
            log.error("execute error , cur node : {}", node);
            e.printStackTrace();
        }
        //执行完成移除出缓存
        executingNodeMap.remove(String.format("%s&%s", context.getLogger().getUuid(), node.getId()));
        //推送节点状态和所有输出的边
        eventBus.publish(new PipelineNodeRefreshEvent(context.getLogger().getUuid(), node, context.getTargetLineMap()));
    }

    /**
     * 判断是否可执行当前节点（检查当前节点是否所有输入节点都已经执行完成）
     *
     * @param node 当前节点
     * @return
     */
    private synchronized boolean executable(PipelineExecuteContext context, Nodes node) {
        try {
            log.info("检查是否所有输入节点都已经执行完成:{}", node.getName());
            if (NodeExecuteStatus.SUCCESS.getName().equals(node.getData().getNodeState())) {
                return false;
            }
            //判断当前节点的所有前置节点是否已经执行完成
            List<String> targets = node.getPoints().getTargets();
            for (String t : targets) {
                String targetUUID = t.replace("target-", "");
                List<String> sourceUUIDList = context.getSourceLineMap().getOrDefault(targetUUID, Collections.emptyList());
                for (String sourceUUID : sourceUUIDList) {
                    Nodes sourceNode = context.getSourceMap().get(sourceUUID);
                    if (Objects.nonNull(sourceNode) && (Objects.isNull(sourceNode.getData().getNodeState()) ||
                            StringUtils.isBlank(sourceNode.getData().getNodeState()) ||
                            NodeExecuteStatus.LOADING.getName().equals(sourceNode.getData().getNodeState()) ||
                            NodeExecuteStatus.FAILED.getName().equals(sourceNode.getData().getNodeState()))) {
                        log.info("输入节点:{} 未执行完成，放弃执行当前节点 {}", sourceNode, node);
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }

    public NodeLogger getExecuteNode(String key) {
        return executingNodeMap.get(key);
    }
}
