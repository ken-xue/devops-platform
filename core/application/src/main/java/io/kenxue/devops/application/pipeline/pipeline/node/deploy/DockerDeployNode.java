package io.kenxue.devops.application.pipeline.pipeline.node.deploy;

import io.kenxue.devops.application.pipeline.pipeline.node.common.AbstractNode;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.context.Result;
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
