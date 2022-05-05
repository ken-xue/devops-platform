package io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.command;

import io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
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
        NodeExecuteLogger nodeExecuteLogger = nodeExecuteLogger2DTOAssembler.toDomain(cmd.getNodeExecuteLoggerDTO());
        nodeExecuteLoggerRepository.update(nodeExecuteLogger);
        return Response.success();
    }
}