package io.kenxue.cicd.application.pipeline.pipeline.node;

import com.alibaba.fastjson.JSON;
import io.kenxue.cicd.application.pipeline.pipeline.command.PipelineExecuteContext;
import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.cicd.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import io.kenxue.cicd.sharedataboject.pipeline.enums.NodeEnum;
import io.kenxue.cicd.sharedataboject.pipeline.enums.NodeExecuteStatus;
import io.kenxue.cicd.sharedataboject.pipeline.enums.PipelineBuildResultEnum;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
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
            Thread.sleep(5000);
            PipelineExecuteContext context = (PipelineExecuteContext) executeContext;
            List<Nodes> nodes = context.getNodes();
            nodes.forEach(v-> {
                if(NodeEnum.END.getName().equals(v.getName())){
                    v.refreshStatus(NodeExecuteStatus.SUCCESS);
                }
            });
            context.getGraph().setNodes(nodes);
            PipelineExecuteLogger logger = context.getLogger();
            logger.setExecuteEndTime(new Date());
            logger.setGraphContent(JSON.toJSONString(context.getGraph()));
            logger.setFinalStatus(PipelineBuildResultEnum.SUCCESS.getDesc());
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
