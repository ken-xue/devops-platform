package io.kenxue.cicd.application.pipeline.logger.node.command;

import io.kenxue.cicd.application.pipeline.logger.node.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerUpdateCmd;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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