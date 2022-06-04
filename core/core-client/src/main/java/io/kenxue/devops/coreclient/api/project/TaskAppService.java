package io.kenxue.devops.coreclient.api.project;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.task.*;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
public interface TaskAppService {
    Response add(TaskAddCmd cmd);
    Response update(TaskUpdateCmd cmd);
    Response delete(TaskDeleteCmd userDeleteCmd);
    SingleResponse<TaskDTO> getById(TaskGetQry qry);
    MultiResponse<TaskDTO> list(TaskListQry qry);
    PageResponse<TaskDTO> page(TaskPageQry userPageQry);
}
