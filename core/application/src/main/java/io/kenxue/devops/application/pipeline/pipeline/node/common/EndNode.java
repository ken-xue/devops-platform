package io.kenxue.devops.application.pipeline.pipeline.node.common;

import com.alibaba.fastjson.JSON;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import io.kenxue.devops.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.context.Result;
import io.kenxue.devops.sharedataboject.pipeline.enums.NodeEnum;
import io.kenxue.devops.sharedataboject.pipeline.enums.NodeExecuteStatus;
import io.kenxue.devops.sharedataboject.pipeline.enums.PipelineBuildResultEnum;
import io.kenxue.devops.sharedataboject.pipeline.graph.Nodes;
import io.kenxue.devops.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 流水线后置节点,可以做一些后置如清理工作空间的工作
 * @author biaoyang
 */
@Service
@Slf4j
public class EndNode implements Node {

    @Resource
    private PipelineExecuteLoggerRepository loggerRepository;

    @Override
    public Result execute(ExecuteContext executeContext) {
        log.info(getName());
        try {
            PipelineExecuteContext context = (PipelineExecuteContext) executeContext;
            PipelineExecuteLogger logger = context.getLogger();
            List<Nodes> nodes = context.getNodes();
            Thread.sleep(5000);
            //只有全部节点执行成功当前流水线才能算执行成功
            if (!PipelineBuildResultEnum.FAILED.getDesc().equals(logger.getFinalStatus())) {
                nodes.forEach(v -> {
                    if (NodeEnum.END.getName().equals(v.getName())) {
                        v.refreshStatus(NodeExecuteStatus.SUCCESS);
                    }
                });
                logger.setFinalStatus(PipelineBuildResultEnum.SUCCESS.getDesc());
            }
            context.getGraph().setNodes(nodes);
            logger.setExecuteEndTime(new Date());
            logger.setGraphContent(JSON.toJSONString(context.getGraph()));
            //更新流水线各个节点执行状态
            loggerRepository.updateByUuid(logger);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new DefaultResult();
    }

    @Override
    public String getName() {
        return "END";
    }
}
