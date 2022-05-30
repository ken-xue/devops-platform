package io.kenxue.devops.coreclient.api.pipeline;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.*;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
public interface PipelineExecuteLoggerAppService {
    Response add(PipelineExecuteLoggerAddCmd cmd);
    Response update(PipelineExecuteLoggerUpdateCmd cmd);
    Response delete(PipelineExecuteLoggerDeleteCmd userDeleteCmd);
    SingleResponse<PipelineExecuteLoggerDTO> getById(PipelineExecuteLoggerGetQry qry);
    MultiResponse<PipelineExecuteLoggerDTO> list(PipelineExecuteLoggerListQry qry);
    PageResponse<PipelineExecuteLoggerDTO> page(PipelineExecuteLoggerPageQry userPageQry);
}
