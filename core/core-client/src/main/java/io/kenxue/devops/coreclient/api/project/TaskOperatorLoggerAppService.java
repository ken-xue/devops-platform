package io.kenxue.devops.coreclient.api.project;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.*;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public interface TaskOperatorLoggerAppService {
    Response add(TaskOperatorLoggerAddCmd cmd);
    Response update(TaskOperatorLoggerUpdateCmd cmd);
    Response delete(TaskOperatorLoggerDeleteCmd userDeleteCmd);
    SingleResponse<TaskOperatorLoggerDTO> getById(TaskOperatorLoggerGetQry qry);
    MultiResponse<TaskOperatorLoggerDTO> list(TaskOperatorLoggerListQry qry);
    PageResponse<TaskOperatorLoggerDTO> page(TaskOperatorLoggerPageQry userPageQry);
}
