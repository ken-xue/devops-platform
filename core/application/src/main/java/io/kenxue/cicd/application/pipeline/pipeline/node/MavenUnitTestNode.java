package io.kenxue.cicd.application.pipeline.pipeline.node;


import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MavenUnitTestNode implements Node {

    @Override
    public Result execute(ExecuteContext executeContext) {
        log.error(getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "MAVEN_UNIT_TEST";
    }
}
