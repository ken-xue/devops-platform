package io.kenxue.cicd.domain.repository.pipeline;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerListQry;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerPageQry;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;

import java.util.List;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
public interface NodeExecuteLoggerRepository {
    void create(NodeLogger nodeLogger);
    void update(NodeLogger nodeLogger);
    NodeLogger getById(Long id);
    List<NodeLogger> list(NodeExecuteLoggerListQry nodeExecuteLoggerListQry);
    Page<NodeLogger> page(NodeExecuteLoggerPageQry qry);

    /**
     * 查询执行日志
     * @param loggerUUID 执行日志uuid
     * @param nodeUUID 节点uuid
     * @return
     */
    NodeLogger getByLoggerUUIDAndNodeUUID(String loggerUUID, String nodeUUID);
}
