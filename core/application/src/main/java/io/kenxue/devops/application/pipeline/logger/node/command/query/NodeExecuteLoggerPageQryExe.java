package io.kenxue.devops.application.pipeline.logger.node.command.query;

import io.kenxue.devops.application.pipeline.logger.node.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLoggerPageQryExe {

    @Resource
    private NodeExecuteLoggerRepository nodeExecuteLoggerRepository;
    @Resource
    private NodeExecuteLogger2DTOAssembler nodeExecuteLogger2DTOAssembler;

    public PageResponse<NodeExecuteLoggerDTO> execute(NodeExecuteLoggerPageQry qry) {
        Page<NodeLogger> page = nodeExecuteLoggerRepository.page(qry);
        return PageResponse.of(nodeExecuteLogger2DTOAssembler.toDTOPage(page));
    }
}
