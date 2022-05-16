package io.kenxue.cicd.application.pipeline.pipeline.node.builder;

import com.alibaba.fastjson.JSON;
import io.kenxue.cicd.application.pipeline.pipeline.node.common.AbstractNode;
import io.kenxue.cicd.application.pipeline.pipeline.node.common.PipelineExecuteContext;
import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * java构建step
 */
@Service
@Slf4j
public class JavaBuildNode extends AbstractNode {

    @Override
    public Result execute(ExecuteContext executeContext) {
        PipelineExecuteContext context = (PipelineExecuteContext)executeContext;
        NodeLogger logger = (NodeLogger) context.getAttributes(getName()+"logger");
        log.info(getName());
        DefaultResult defaultResult = new DefaultResult();
        ApplicationInfo application = context.getApplication();
        //https://gitee.com/ken_xue/cicd-platform.git
        String repository = application.getRepository();
        String dir = repository.substring(repository.lastIndexOf("/")+1,repository.lastIndexOf("."));
        //配置的节点数据
        PipelineNodeInfo nodeInfo = (PipelineNodeInfo) context.getAttributes(getName());
        JavaBuildNodeConfig javaBuildNodeConfig = JSON.parseObject(nodeInfo.getInfo(), JavaBuildNodeConfig.class);
        //打包路径
        StringBuilder packageCmd = new StringBuilder("tar -czvf package.tar.gz ");
        javaBuildNodeConfig.getPackagePaths().forEach(path->{
            packageCmd.append(path).append(" ");
        });
        shell(logger, context
                , String.format("mkdir -p /home/admin/%s", context.getLogger().getUuid())
                , String.format("cd /home/admin/%s",context.getLogger().getUuid())
                , "git --version"
                , "rm -rf cicd-platform"
                , String.format("git clone %s",repository)
                , String.format("cd %s",dir)
                , "mvn -v"
                , javaBuildNodeConfig.getBuildScript()
                , packageCmd.toString()
//                ,"scp package.tar.gz"//上传构建文件到文件服务器
                , String.format("rm -rf /home/admin/%s",context.getLogger().getUuid())
        );

        log.error("{}节点执行完成", getName());
        return defaultResult;
    }

    @Override
    public String getName() {
        return "JAVA_BUILD";
    }

}

