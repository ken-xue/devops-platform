package io.kenxue.devops.application.pipeline.pipeline.node.builder;

import com.alibaba.fastjson.JSON;
import io.kenxue.devops.application.common.util.StringUtil;
import io.kenxue.devops.application.pipeline.pipeline.node.common.AbstractNode;
import io.kenxue.devops.application.pipeline.pipeline.node.common.PipelineExecuteContext;
import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.devops.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.context.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * java构建step
 */
@Service
@Slf4j
public class JavaBuildNode extends AbstractNode {

    @Resource
    private NodeExecuteLoggerRepository loggerRepository;
    @Override
    public Result execute(ExecuteContext executeContext) {
        PipelineExecuteContext context = (PipelineExecuteContext)executeContext;
        NodeLogger logger = (NodeLogger) context.getAttributes(getName()+"logger");
        DefaultResult defaultResult = new DefaultResult();
        ApplicationInfo application = context.getApplication();
        String repository = application.getRepository();
        //配置的节点数据
        PipelineNodeInfo nodeInfo = (PipelineNodeInfo) context.getAttributes(getName());
        JavaBuildNodeConfig javaBuildNodeConfig = JSON.parseObject(nodeInfo.getInfo(), JavaBuildNodeConfig.class);
        //需要打包的文件路径
        String packageName = "package.tar.gz";
        StringBuilder packageCmd = new StringBuilder("tar -czvf ").append(packageName).append(" ");
        javaBuildNodeConfig.getPackagePaths().forEach(path-> packageCmd.append(path).append(" "));
        //git仓库名
        String repositoryName = StringUtil.getRepositoryName(repository);
        String uploadPath = "minio/pipeline-node-execute-result-bucket/" +DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyy-MM-dd-HH-mm-ss")+"/";
        defaultResult.add("target",uploadPath+packageName);
        shell(logger, context
                , cmd("mkdir -p /home/admin/", context.getLogger().getUuid())
                , cmd("cd /home/admin/",context.getLogger().getUuid())
                , "git --version"
                , cmd("rm -rf ",repositoryName)
                , cmd("git clone ",repository)
                , cmd("cd ",repositoryName)
                , "mvn -v"
                , javaBuildNodeConfig.getBuildScript()
                , packageCmd.toString()
//                , cmd("mc cp /home/admin/",context.getLogger().getUuid(),"/",repositoryName,"/package.tar.gz ",uploadPath
//                        ," && ",cmd("rm -rf /home/admin/",context.getLogger().getUuid())," && exit")//上传构建文件到文件服务器
                ,cmd("rm -rf /home/admin/",context.getLogger().getUuid())//上传构建文件到文件服务器
        );
        logger.create();
        logger.serializable();//序列化
        logger.setExecuteEndTime(new Date());
        loggerRepository.create(logger);
        log.debug("{}节点执行完成", getName());
        return defaultResult;
    }

    @Override
    public String getName() {
        return "JAVA_BUILD";
    }

}

