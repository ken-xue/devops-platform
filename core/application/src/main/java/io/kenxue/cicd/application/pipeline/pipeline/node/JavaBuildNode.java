package io.kenxue.cicd.application.pipeline.pipeline.node;

import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * java构建step
 */
@Service
@Slf4j
public class JavaBuildNode implements Node {

    @Override
    public Result execute(ExecuteContext executeContext) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error(getName());
        // 1.连接Jenkins
        // 2.获取任务模板
        // 3.构建任务数据executeContext
        // 4.发送执行任务
        Object attributes = executeContext.getAttributes(getName());
        DefaultResult defaultResult = new DefaultResult();
        log.info("attr : {}",attributes);

        return defaultResult;
    }

    @Override
    public String getName() {
        return "JAVA_BUILD";
    }
}
