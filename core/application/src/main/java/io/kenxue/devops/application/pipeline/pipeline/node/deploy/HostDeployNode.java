package io.kenxue.devops.application.pipeline.pipeline.node.deploy;

import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.context.Result;
import io.kenxue.devops.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 主机部署step
 */
@Service
@Slf4j
public class HostDeployNode implements Node {

    @Override
    public Result execute(ExecuteContext executeContext) {
        log.info(getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "HOST_DEPLOY";
    }
}
