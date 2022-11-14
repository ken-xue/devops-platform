package io.kenxue.devops.application.pipeline.pipeline.command;

import com.alibaba.fastjson.JSON;
import io.kenxue.devops.application.common.event.EventBusI;
import io.kenxue.devops.application.pipeline.pipeline.engine.EngineI;
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
 * 流水线
 *
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@Service
public class PipelineExecuteCmdExe {

    @Resource
    private PipelineExecuteLoggerRepository loggerRepository;
    @Resource
    private PipelineRepository pipelineRepository;
    @Resource
    private PipelineNodeInfoRepository nodeInfoRepository;
    @Resource
    private ApplicationInfoRepository applicationInfoRepository;
    @Resource
    private EngineI engine;

    /**
     * 流水线执行入口
     *
     * @param cmd
     * @return
     */
    public Response execute(PipelineExecuteCmd cmd) {

        Pipeline pipeline = pipelineRepository.getById(cmd.getId());

        pipeline.setLatestTriggerTime(new Date());

        pipelineRepository.update(pipeline);

        Assert.isTrue(Objects.nonNull(pipeline),"请先保存流水线");

        PipelineExecuteContext context = buildContext(pipeline);

        context.setLogger(logger(pipeline,cmd));

        engine.execute(context);

        return SingleResponse.of(context.getLogger());
    }

    /**
     * 生成执行记录
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
//                throw new RuntimeException(String.format("节点%s配置为空",node.getName()));
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

        ApplicationInfo application = applicationInfoRepository.getByUuid(pipeline.getApplicationUuid());
        context.setApplication(application);

        return context;
    }
}
