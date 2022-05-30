package io.kenxue.devops.application.pipeline.logger.node.command;

import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.kenxue.devops.application.pipeline.logger.node.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerAddCmd;
import io.kenxue.devops.sharedataboject.common.obs.BucketEnum;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLoggerAddCmdExe {

    @Resource
    private NodeExecuteLoggerRepository nodeExecuteLoggerRepository;
    @Resource
    private NodeExecuteLogger2DTOAssembler nodeExecuteLogger2DTOAssembler;
    @Resource
    private ObjectStorageService objectStorageService;

    public Response execute(NodeExecuteLoggerAddCmd cmd) {
        NodeLogger logger = nodeExecuteLogger2DTOAssembler.toDomain(cmd.getNodeExecuteLoggerDTO());
        logger.create(UserThreadContext.get());
        String content = logger.getLogger();
        String nodeData = logger.getNodeData();
        objectStorageService.setString(BucketEnum.PIPELINE_NODE_LOGGER.getName(),logger.getUuid(),content);
        objectStorageService.setString(BucketEnum.PIPELINE_NODE_EXECUTE_RESULT.getName(),logger.getUuid(),nodeData);
        nodeExecuteLoggerRepository.create(logger);
        return Response.success();
    }
}
