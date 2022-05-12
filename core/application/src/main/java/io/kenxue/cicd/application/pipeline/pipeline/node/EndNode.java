package io.kenxue.cicd.application.pipeline.pipeline.node;

import io.kenxue.cicd.application.pipeline.pipeline.command.PipelineExecuteCmdExe;
import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 流水线后置节点
 * @author biaoyang
 */
@Service
@Slf4j
public class EndNode implements Node {

    @Resource
    private PipelineExecuteCmdExe pipelineExecuteCmdExe;

    @Override
    public Result execute(ExecuteContext executeContext) {
        log.error(getName());
        try {
            Thread.sleep(5000);
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
