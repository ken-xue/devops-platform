package io.kenxue.devops.application.pipeline.pipeline.command;

import com.alibaba.fastjson.JSON;
import io.kenxue.devops.application.common.event.EventBusI;
import io.kenxue.devops.application.pipeline.pipeline.manager.PipelineNodeManager;
import io.kenxue.devops.application.pipeline.pipeline.node.common.PipelineExecuteContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineExecuteCmd;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.event.PipelineNodeRefreshEvent;
import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.domain.domain.pipeline.Pipeline;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.devops.domain.factory.pipeline.NodeExecuteLoggerFactory;
import io.kenxue.devops.domain.factory.pipeline.PipelineExecuteLoggerFactory;
import io.kenxue.devops.domain.repository.application.ApplicationInfoRepository;
import io.kenxue.devops.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import io.kenxue.devops.domain.repository.pipeline.PipelineNodeInfoRepository;
import io.kenxue.devops.domain.repository.pipeline.PipelineRepository;
import io.kenxue.devops.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.devops.sharedataboject.pipeline.context.Result;
import io.kenxue.devops.sharedataboject.pipeline.enums.NodeEnum;
import io.kenxue.devops.sharedataboject.pipeline.enums.NodeExecuteStatus;
import io.kenxue.devops.sharedataboject.pipeline.enums.PipelineBuildResultEnum;
import io.kenxue.devops.sharedataboject.pipeline.graph.Graph;
import io.kenxue.devops.sharedataboject.pipeline.graph.Nodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

/**
 * ?????????
 *
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@Service
public class PipelineExecuteCmdExe implements DisposableBean {
    /**
     * ???????????????
     */
    ExecutorService defaultExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1,
            10L,TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000));

    //??????????????????????????? k=??????uuid+"&"+??????uuid
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
    @Resource
    private ApplicationInfoRepository applicationInfoRepository;

    /**
     * ?????????????????????
     *
     * @param cmd
     * @return
     */
    public Response execute(PipelineExecuteCmd cmd) {

        Pipeline pipeline = pipelineRepository.getById(cmd.getId());

        pipeline.setLatestTriggerTime(new Date());

        pipelineRepository.update(pipeline);

        Assert.isTrue(Objects.nonNull(pipeline),"?????????????????????");

        PipelineExecuteContext context = buildContext(pipeline);

        context.setLogger(logger(pipeline,cmd));

        defaultExecutor.submit(() -> {
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
     * ??????????????????
     *
     * @param pipeline
     */
    private PipelineExecuteLogger logger(Pipeline pipeline,PipelineExecuteCmd cmd) {
        PipelineExecuteLogger logger = PipelineExecuteLoggerFactory.getPipelineExecuteLogger();
        logger.create(cmd.getTargetUser());
        logger.setTargetWay(cmd.getTargetWay().getDesc());
        logger.setPipelineUuid(pipeline.getUuid());
        logger.setExecuteStartTime(new Date());
        logger.setGraphContent(JSON.toJSONString(pipeline.getGraph()));
        loggerRepository.create(logger);
        return logger;
    }

    /**
     * ???????????????
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
            //?????????????????????????????????
            if (NodeEnum.START.getName().equals(node.getName()) || NodeEnum.END.getName().equals(node.getName())) {
                continue;
            }
            PipelineNodeInfo nodeInfo = nodeInfoRepository.getByNodeId(node.getId());
            if (Objects.isNull(nodeInfo)) {
                log.error("node : {},config node info data is null", node);
//                throw new RuntimeException(String.format("??????%s????????????",node.getName()));
            } else {
                context.setAttributes(node.getName(), nodeInfo);
            }
        }
        //??????????????????
        context.setStart(nodes.stream().filter(node -> NodeEnum.START.toString().equals(node.getName())).findFirst().get());
        //????????????????????????????????????????????????
        for (Nodes n : nodes) {
            //????????????n??????target
            n.getPoints().getTargets().forEach(uuid -> context.getTargetMap().put(uuid.replace("target-", ""), n));
            //????????????n??????source
            n.getPoints().getSources().forEach(uuid -> context.getSourceMap().put(uuid.replace("source-", ""), n));
        }
        //??????????????????
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

        ApplicationInfo application = applicationInfoRepository.getByUuid(pipeline.getApplicationUuid());
        context.setApplication(application);

        return context;
    }


    /**
     * ???????????????
     *
     * @param context
     * @param node
     */
    public void execute(PipelineExecuteContext context, Nodes node) {

        //?????????????????????
        if (!executable(context, node)) {
            return;
        }

        try {
            log.info("??????????????????{}", node.getName());
            //????????????
            Result result = new DefaultResult();
            //????????????//?????????
            node.refreshStatus(NodeExecuteStatus.LOADING);
            //????????????
            NodeLogger logger = NodeExecuteLoggerFactory.getNodeExecuteLogger().setExecuteStartTime(new Date());
            context.setAttributes(node.getName()+"logger",logger);
            executingNodeMap.put(String.format("%s&%s", context.getLogger().getUuid(), node.getId()), logger);
            //????????????
            eventBus.publish(new PipelineNodeRefreshEvent(context.getLogger().getUuid(), node, context.getSourceLineMap(), NodeExecuteStatus.LOADING));
            //????????????
            context.setAttributes(node.getName() + "logger-uuid", context.getLogger().getUuid());
            context.setAttributes(node.getName() + "node-uuid", node.getId());
            Result ret = pipelineNodeManager.get(node.getName()).execute(context);
            result.add(node.getName(), ret);
            //????????????
            node.refreshStatus(NodeExecuteStatus.SUCCESS);
            //??????????????????????????????
            List<String> sources = node.getPoints().getSources();
            sources.forEach(sce -> {
                String next = sce.replace("source-", "");
                List<String> list = context.getTargetLineMap().getOrDefault(next, Collections.emptyList());
                list.forEach(v -> defaultExecutor.submit(() -> execute(context, context.getTargetMap().get(v))));
            });

        } catch (Exception e) {
            //????????????
            node.refreshStatus(NodeExecuteStatus.FAILED);
            context.getLogger().setFinalStatus(PipelineBuildResultEnum.FAILED.getDesc());//?????????????????????????????????????????????
            log.error("execute error , cur node : {}", node);
            e.printStackTrace();
        }
        //???????????????????????????
        executingNodeMap.remove(String.format("%s&%s", context.getLogger().getUuid(), node.getId()));
        //????????????
        PipelineExecuteLogger logger = context.getLogger();
        context.getGraph().setNodes(context.getNodes());
        logger.setGraphContent(JSON.toJSONString(context.getGraph()));
        loggerRepository.updateByUuid(logger);
        //???????????????????????????????????????
        eventBus.publish(new PipelineNodeRefreshEvent(context.getLogger().getUuid(), node, context.getTargetLineMap()));
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param node ????????????
     * @return
     */
    private synchronized boolean executable(PipelineExecuteContext context, Nodes node) {
        try {
            log.info("???????????????????????????????????????????????????:{}", node.getName());
            if (NodeExecuteStatus.SUCCESS.getName().equals(node.getData().getNodeState())) return false;
            //???????????????????????????????????????????????????????????????
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
                        log.info("????????????:{} ?????????????????????????????????????????? {}", sourceNode, node);
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

    public NodeLogger getExecuteNode(String key) {
        return executingNodeMap.get(key);
    }
}
