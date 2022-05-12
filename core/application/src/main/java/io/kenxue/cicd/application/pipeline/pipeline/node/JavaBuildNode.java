package io.kenxue.cicd.application.pipeline.pipeline.node;

import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.factory.pipeline.NodeExecuteLoggerFactory;
import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * java构建step
 */
@Service
@Slf4j
public class JavaBuildNode extends AbstractNode {

    @Override
    public Result execute(ExecuteContext context) {

//        NodeLogger logger = NodeExecuteLoggerFactory.getNodeExecuteLogger().setExecuteStartTime(new Date());
        NodeLogger logger = (NodeLogger) context.getAttributes(getName()+"logger");
        log.error(getName());
        Object attributes = context.getAttributes(getName());
        DefaultResult defaultResult = new DefaultResult();
        log.info("attr : {}", attributes);

        shell(logger, context, "cd /home/admin/"
                , "git -v"
                , "rm -rf cicd-platform"
                , "git clone https://gitee.com/ken_xue/cicd-platform.git"
                , "cd cicd-platform"
                , "mvn -v"
                , "mvn clean install -Dmaven.test.skip=true"
                , "cd starter/"
                , "ls target"
                , "tar -czvf package.tar.gz target/cicd-platform.jar");

        log.error("{}节点执行完成", getName());
        return defaultResult;
    }

    @Override
    public String getName() {
        return "JAVA_BUILD";
    }

}

