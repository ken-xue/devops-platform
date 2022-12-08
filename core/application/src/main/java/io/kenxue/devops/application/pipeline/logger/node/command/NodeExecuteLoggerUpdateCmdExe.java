package io.kenxue.devops.application.pipeline.logger.node.command;

import io.kenxue.devops.application.pipeline.logger.node.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerUpdateCmd;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLoggerUpdateCmdExe {

    @Resource
    private NodeExecuteLoggerRepository nodeExecuteLoggerRepository;
    @Resource
    private NodeExecuteLogger2DTOAssembler nodeExecuteLogger2DTOAssembler;

    public Response execute(NodeExecuteLoggerUpdateCmd cmd) {
        NodeLogger nodeLogger = nodeExecuteLogger2DTOAssembler.toDomain(cmd.getNodeExecuteLoggerDTO());
        nodeExecuteLoggerRepository.update(nodeLogger);
        return Response.success();
    }
}