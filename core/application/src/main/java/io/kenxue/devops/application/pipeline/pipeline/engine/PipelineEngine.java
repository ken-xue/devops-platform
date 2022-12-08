package io.kenxue.devops.application.pipeline.pipeline.engine;

import com.alibaba.fastjson.JSON;
import io.kenxue.devops.application.common.event.EventBusI;
import io.kenxue.devops.application.pipeline.pipeline.manager.PipelineNodeManager;
import io.kenxue.devops.application.pipeline.pipeline.node.common.PipelineExecuteContext;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.event.PipelineNodeRefreshEvent;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.domain.factory.pipeline.NodeExecuteLoggerFactory;
import io.kenxue.devops.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import io.kenxue.devops.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.context.Result;
import io.kenxue.devops.sharedataboject.pipeline.enums.NodeExecuteStatus;
import io.kenxue.devops.sharedataboject.pipeline.enums.PipelineBuildResultEnum;
import io.kenxue.devops.sharedataboject.pipeline.graph.Nodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
@Service
public class PipelineEngine implements EngineI, DisposableBean,PipelineExecute {


    @Resource
    private EventBusI eventBus;
    @Resource
    private PipelineNodeManager pipelineNodeManager;
    @Resource
    private PipelineExecuteLoggerRepository loggerRepository;


    /**
     * 默认线程池
     */
//    ExecutorService defaultExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
//            Runtime.getRuntime().availableProcessors() + 1,
//            10L, TimeUnit.SECONDS,
//            new LinkedBlockingQueue<>(1000));
    ExecutorService defaultExecutor = new ThreadPoolExecutor(
            2,
            2,
            10L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000));

    //当前正在执行的节点 k=记录uuid+"&"+节点uuid
    private static volatile ConcurrentHashMap<String, NodeLogger> executingNodeMap = new ConcurrentHashMap<>(2 << 4);

    /**
     * 执行流水线
     * @param context
     */
    @Override
    public void execute(ExecuteContext context) {
        PipelineExecuteContext ctx = (PipelineExecuteContext)context;
        defaultExecutor.submit(() -> execute0(ctx, ctx.getStart()));
    }
    /**
     * 执行流水线
     * @param ctx
     * @param node
     */
    private void execute0(PipelineExecuteContext ctx, Nodes node) {
        //判断是否可执行
        if (executable(ctx, node)) {
            try {
                log.info("执行的节点：{}", node.getName());
                //执行结果
                Result result = new DefaultResult();
                //变更状态//进行中
                node.refreshStatus(NodeExecuteStatus.LOADING);
                //加入缓存
                NodeLogger logger = NodeExecuteLoggerFactory.getNodeExecuteLogger().setExecuteStartTime(new Date());
                ctx.setAttributes(node.getName() + "logger", logger);
                executingNodeMap.put(String.format("%s&%s", ctx.getLogger().getUuid(), node.getId()), logger);
                //发送事件
                eventBus.publish(new PipelineNodeRefreshEvent(ctx.getLogger().getUuid(), node, ctx.getSourceLineMap(), NodeExecuteStatus.LOADING));
                //执行节点
                ctx.setAttributes(node.getName() + "logger-uuid", ctx.getLogger().getUuid());
                ctx.setAttributes(node.getName() + "node-uuid", node.getId());
                Result ret = pipelineNodeManager.get(node.getName()).execute(ctx);
                result.add(node.getName(), ret);
                //执行成功
                node.refreshStatus(NodeExecuteStatus.SUCCESS);
                //获取下一个执行的路线
                List<String> sources = node.getPoints().getSources();
                sources.forEach(sce -> {
                    String next = sce.replace("source-", "");
                    List<String> list = ctx.getTargetLineMap().getOrDefault(next, Collections.emptyList());
                    list.forEach(v -> defaultExecutor.submit(() -> execute0(ctx, ctx.getTargetMap().get(v))));
                });

            } catch (Exception e) {
                //执行失败
                node.refreshStatus(NodeExecuteStatus.FAILED);
                ctx.getLogger().setFinalStatus(PipelineBuildResultEnum.FAILED.getDesc());//要全部节点通过才算最终执行成功
                log.error("execute error , current node : {}", node);
                e.printStackTrace();
            }
            //执行完成移除出缓存
            executingNodeMap.remove(String.format("%s&%s", ctx.getLogger().getUuid(), node.getId()));
            //更新日志
            PipelineExecuteLogger logger = ctx.getLogger();
            ctx.getGraph().setNodes(ctx.getNodes());
            logger.setGraphContent(JSON.toJSONString(ctx.getGraph()));
            loggerRepository.updateByUuid(logger);
            //推送节点状态和所有输出的边
            eventBus.publish(new PipelineNodeRefreshEvent(ctx.getLogger().getUuid(), node, ctx.getTargetLineMap()));
        }
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
            if (NodeExecuteStatus.SUCCESS.getName().equals(node.getData().getNodeState())) return false;
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
        if (!defaultExecutor.isShutdown()) {
            defaultExecutor.shutdown();
        }
    }

    @Override
    public NodeLogger getExecuteNode(String key) {
        return executingNodeMap.get(key);
    }
}
