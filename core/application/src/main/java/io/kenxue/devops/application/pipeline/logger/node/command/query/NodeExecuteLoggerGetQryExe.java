package io.kenxue.devops.application.pipeline.logger.node.command.query;

import io.kenxue.devops.application.pipeline.logger.node.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerGetQry;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLoggerGetQryExe {

    @Resource
    private NodeExecuteLoggerRepository nodeExecuteLoggerRepository;
    @Resource
    private NodeExecuteLogger2DTOAssembler nodeExecuteLogger2DTOAssembler;

    public SingleResponse<NodeExecuteLoggerDTO> execute(NodeExecuteLoggerGetQry qry) {
        return SingleResponse.of(nodeExecuteLogger2DTOAssembler.toDTO(nodeExecuteLoggerRepository.getById(qry.getId())));
    }

}
