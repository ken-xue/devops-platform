package io.kenxue.cicd.domain.repository.pipeline;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerListQry;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerPageQry;
import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
import java.util.List;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
public interface NodeExecuteLoggerRepository {
    void create(NodeExecuteLogger nodeExecuteLogger);
    void update(NodeExecuteLogger nodeExecuteLogger);
    NodeExecuteLogger getById(Long id);
    List<NodeExecuteLogger> list(NodeExecuteLoggerListQry nodeExecuteLoggerListQry);
    Page<NodeExecuteLogger> page(NodeExecuteLoggerPageQry qry);
}
