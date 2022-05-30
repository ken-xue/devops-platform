package io.kenxue.devops.coreclient.api.pipeline;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.*;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
public interface NodeExecuteLoggerAppService {
    Response add(NodeExecuteLoggerAddCmd cmd);
    Response update(NodeExecuteLoggerUpdateCmd cmd);
    Response delete(NodeExecuteLoggerDeleteCmd userDeleteCmd);
    SingleResponse<NodeExecuteLoggerDTO> getById(NodeExecuteLoggerGetQry qry);
    MultiResponse<NodeExecuteLoggerDTO> list(NodeExecuteLoggerListQry qry);
    PageResponse<NodeExecuteLoggerDTO> page(NodeExecuteLoggerPageQry userPageQry);

    /**
     * 通过node-uuid和logger-uuid查询日志详情
     * @param nodeExecuteLoggerGetQry
     * @return
     */
    Response getNodeAndLoggerUuId(NodeExecuteLoggerGetQry nodeExecuteLoggerGetQry);
}
