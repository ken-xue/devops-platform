package io.kenxue.devops.application.pipeline.pipeline.node.common;

import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.context.Result;
import io.kenxue.devops.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StartNode implements Node {

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
        return "START";
    }
}
