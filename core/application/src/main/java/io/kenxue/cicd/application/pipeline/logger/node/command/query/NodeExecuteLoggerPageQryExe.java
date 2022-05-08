package io.kenxue.cicd.application.pipeline.logger.node.command.query;

import io.kenxue.cicd.application.pipeline.logger.node.assembler.NodeExecuteLogger2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerPageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
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
        Page<NodeExecuteLogger> page = nodeExecuteLoggerRepository.page(qry);
        return PageResponse.of(nodeExecuteLogger2DTOAssembler.toDTOPage(page));
    }
}
