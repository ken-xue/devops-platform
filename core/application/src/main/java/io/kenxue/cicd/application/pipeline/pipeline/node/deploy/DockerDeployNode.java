package io.kenxue.cicd.application.pipeline.pipeline.node.deploy;

import io.kenxue.cicd.application.pipeline.pipeline.node.common.AbstractNode;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import org.springframework.stereotype.Service;

@Service
public class DockerDeployNode extends AbstractNode {
    @Override
    public Result execute(ExecuteContext context) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "DOCKER_DEPLOYMENT";
    }
}
