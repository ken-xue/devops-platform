package io.kenxue.cicd.application.pipeline.pipeline.node;

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
public class JavaImageConstructionNode implements Node {

    @Override
    public Result execute(ExecuteContext executeContext) {
        log.info(getName());
        Object attributes = executeContext.getAttributes(getName());
        DefaultResult defaultResult = new DefaultResult();
        log.info("attr : {}",attributes.toString());
        return defaultResult;
    }

    @Override
    public String getName() {
        return "JAVA_IMAGE_CONSTRUCTION";
    }
}
