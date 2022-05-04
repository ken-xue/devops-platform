package io.kenxue.cicd.application.pipeline.pipeline.node;

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
        // 1.连接Jenkins
        // 2.获取任务模板
        // 3.构建任务数据executeContext
        // 4.发送执行任务
        log.info(getName());
        return null;
    }

    @Override
    public String getName() {
        return "JAVA_BUILD";
    }
}
