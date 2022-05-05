package io.kenxue.cicd.application.pipeline.pipeline.command;

import io.kenxue.cicd.application.pipeline.pipeline.manager.NodeManager;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineExecuteCmd;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import io.kenxue.cicd.domain.repository.pipeline.PipelineNodeInfoRepository;
import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import io.kenxue.cicd.sharedataboject.pipeline.enums.NodeEnum;
import io.kenxue.cicd.sharedataboject.pipeline.enums.NodeExecuteStatus;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Graph;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

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
@Component
public class ApplicationPipelineExecuteCmdExe {

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 20L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

    //当前正在执行的实例k=执行记录 uuid，v=pipeline
    private static volatile ConcurrentHashMap<String, Pipeline> executing = new ConcurrentHashMap<>(2 << 4);

    private volatile List<String> edges;

    private volatile Nodes start;

    private volatile Map<String, List<String>> lineMap = new HashMap<>(2 << 4);

    private volatile Map<String, List<String>> sourceLineMap = new HashMap<>(2 << 4);

    private volatile Map<String, Nodes> targetMap = new HashMap<>(2 << 4);

    private volatile Map<String, Nodes> sourceMap = new HashMap<>(2 << 4);

    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;
    @Resource
    private PipelineNodeInfoRepository pipelineNodeInfoRepository;
    @Resource
    private NodeManager nodeManager;
//    @Resource
//    private PipelineExecute

    /**
     * 两个入口
     * 1.打开页面点击执行
     *      1.1 生成执行记录id
     *      1.2 建立节点实时状态socket推送
     * 2.点击执行记录
     *      2.1 执行中（根据执行记录id判断是否正在执行，如果是正在执行建立socket连接）
     *      2.2 已经执行完成（返回执行记录结果即可，无需建立socket连接）
     * @param cmd
     * @return
     */
    public Response execute(ApplicationPipelineExecuteCmd cmd) {

        Pipeline pipeline = applicationPipelineRepository.getById(cmd.getId());

        ExecuteContext context = buildContext(pipeline);

        prepare(pipeline);



        execute(context, start);

        return Response.success();
    }

    /**
     * 构建上下文
     *
     * @param pipeline
     */
    private ExecuteContext buildContext(Pipeline pipeline) {

        ExecuteContext context = new ExecuteContext();

        for (Nodes node : pipeline.getGraph().getNodes()) {
            if (NodeEnum.START.getName().equals(node.getName()) || NodeEnum.END.getName().equals(node.getName()))
                continue;
            PipelineNodeInfo nodeInfo = pipelineNodeInfoRepository.getByNodeId(node.getId());
            if (Objects.isNull(nodeInfo)) {
                log.error("node : {},config node info data is null", node);
            } else {
                context.setAttributes(node.getName(), nodeInfo);
            }
        }

        return context;
    }

    /**
     * 解析流水线
     *
     * @param pipeline
     */
    private void prepare(Pipeline pipeline) {

        lineMap.clear();
        sourceLineMap.clear();
        targetMap.clear();
        sourceMap.clear();

        Graph graph = pipeline.getGraph();
        List<Nodes> nodes = graph.getNodes();
        //获取开始节点
        start = nodes.stream().filter(node -> NodeEnum.START.toString().equals(node.getName())).findFirst().get();
        //移除开始节点
        nodes.remove(start);
        for (Nodes n : nodes) n.getPoints().getTargets().forEach(uuid -> targetMap.put(uuid.replace("target-", ""), n));
        for (Nodes n : nodes) n.getPoints().getSources().forEach(uuid -> sourceMap.put(uuid.replace("source-", ""), n));
        //获取执行路径
        edges = graph.getEdges();
        for (String edge : edges) {
            String[] lines = edge.split("&&");
            String source = lines[0].replace("source-", "");
            List<String> next = lineMap.getOrDefault(source, new LinkedList<>());
            String target = lines[1].replace("target-", "");
            next.add(target);
            lineMap.put(source, next);
            //source
            List<String> sourceLineMapOrDefault = sourceLineMap.getOrDefault(target, new LinkedList<>());
            sourceLineMapOrDefault.add(source);
            sourceLineMap.put(target, sourceLineMapOrDefault);
        }
    }


    /**
     * 执行流水线
     *
     * @param context
     * @param executeNode
     */
    public void execute(ExecuteContext context, Nodes executeNode) {

        if (canExecute(executeNode)) {
            try {
                log.error("执行的节点：{}", executeNode.getName());
                //执行结果
                Result result = new DefaultResult();
                //变更状态
                executeNode.getData().setNodeState(NodeExecuteStatus.LOADING.getName());//进行中
                //TODO:推送节点状态

                //获取下一个执行的路线
                List<String> sources = executeNode.getPoints().getSources();
                //执行
                Result ret = nodeManager.get(executeNode.getName()).execute(context);
                result.add(executeNode.getName(), ret);
                executeNode.getData().setNodeState(NodeExecuteStatus.SUCCESS.getName());//执行成功
                sources.forEach(sce -> {
                    String next = sce.replace("source-", "");
                    List<String> list = lineMap.get(next);
                    list.forEach(v -> executor.submit(() -> {
                        Nodes node = targetMap.get(v);
                        execute(context, node);
                    }));
                });
            } catch (Exception e) {
                executeNode.getData().setNodeState(NodeExecuteStatus.FAILED.getName());//执行失败
                log.error("execute error , cur node : {}", executeNode);
                e.printStackTrace();
            }
            //TODO:推送节点状态
        }
    }

    /**
     * 判断是否可执行当前节点
     *
     * @param executeNode
     * @return
     */
    private synchronized boolean canExecute(Nodes executeNode) {
        try {
            log.info("检查是否所有输入节点都已经执行完成:{}", executeNode.getName());
            //executeNode.get
            if (NodeExecuteStatus.SUCCESS.getName().equals(executeNode.getData().getNodeState())) return false;
            //判断当前节点的所有前置节点是否已经执行完成
            List<String> targets = executeNode.getPoints().getTargets();
            for (String t : targets) {
                String targetUUID = t.replace("target-", "");
                List<String> sourceUUIDList = sourceLineMap.getOrDefault(targetUUID, Collections.emptyList());
                for (String sourceUUID : sourceUUIDList) {
                    Nodes node = sourceMap.get(sourceUUID);
                    log.info("node info:{}", node);
                    if (Objects.nonNull(node) && (Objects.isNull(node.getData().getNodeState()) || StringUtils.isBlank(node.getData().getNodeState()) ||
                            NodeExecuteStatus.LOADING.getName().equals(node.getData().getNodeState()) ||
                            NodeExecuteStatus.FAILED.getName().equals(node.getData().getNodeState()))) {
                        log.info("输入节点:{} 未执行完成，放弃执行当前节点 {}", node, executeNode);
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
}
