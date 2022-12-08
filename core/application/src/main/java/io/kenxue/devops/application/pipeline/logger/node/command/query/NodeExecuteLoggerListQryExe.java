package io.kenxue.devops.application.pipeline.logger.node.command.query;

import io.kenxue.devops.application.pipeline.logger.node.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerListQry;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLoggerListQryExe {
    @Resource
    private NodeExecuteLoggerRepository nodeExecuteLoggerRepository;
    @Resource
    private NodeExecuteLogger2DTOAssembler nodeExecuteLogger2DTOAssembler;

    public MultiResponse<NodeExecuteLoggerDTO> execute(NodeExecuteLoggerListQry qry) {
        List<NodeLogger> nodeLoggerDTOList = nodeExecuteLoggerRepository.list(qry);
        return MultiResponse.of(nodeExecuteLogger2DTOAssembler.toDTOList(nodeLoggerDTOList));
    }
}
