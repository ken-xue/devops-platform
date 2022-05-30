package io.kenxue.devops.application.pipeline.pipeline.node.image;

import io.kenxue.devops.application.pipeline.pipeline.node.common.AbstractNode;
import io.kenxue.devops.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.context.Result;
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
