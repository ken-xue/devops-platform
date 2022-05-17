package io.kenxue.cicd.application.pipeline.pipeline.node.image;

import io.kenxue.cicd.application.pipeline.pipeline.node.common.AbstractNode;
import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Java镜像构建step
 */
@Service
@Slf4j
public class JavaImageConstructionNode extends AbstractNode {

    @Override
    public Result execute(ExecuteContext executeContext) {
        log.info(getName());
        Object attributes = executeContext.getAttributes(getName());
        DefaultResult defaultResult = new DefaultResult();
        log.info("attr : {}",attributes);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return defaultResult;
    }

    @Override
    public String getName() {
        return "JAVA_IMAGE_CONSTRUCTION";
    }
}