package io.kenxue.cicd.application.pipeline.logger.node.command.query;

import io.kenxue.cicd.application.pipeline.logger.node.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerGetQry;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeLoggerGetQryExe {

    @Resource
    private NodeExecuteLoggerRepository nodeExecuteLoggerRepository;
    @Resource
    private NodeExecuteLogger2DTOAssembler nodeExecuteLogger2DTOAssembler;

    public SingleResponse<NodeExecuteLoggerDTO> execute(NodeExecuteLoggerGetQry qry) {
        return SingleResponse.of(nodeExecuteLogger2DTOAssembler.toDTO(nodeExecuteLoggerRepository.getByLoggerUUIDAndNodeUUID(qry.getLoggerUuid(),qry.getNodeUuid())));
    }

}
