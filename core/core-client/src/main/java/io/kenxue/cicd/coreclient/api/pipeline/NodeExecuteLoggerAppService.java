package io.kenxue.cicd.coreclient.api.pipeline;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.*;
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
}
