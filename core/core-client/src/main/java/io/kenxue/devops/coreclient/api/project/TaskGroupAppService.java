package io.kenxue.devops.coreclient.api.project;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskgroup.*;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
public interface TaskGroupAppService {
    Response add(TaskGroupAddCmd cmd);
    Response update(TaskGroupUpdateCmd cmd);
    Response delete(TaskGroupDeleteCmd userDeleteCmd);
    SingleResponse<TaskGroupDTO> getById(TaskGroupGetQry qry);
    MultiResponse<TaskGroupDTO> list(TaskGroupListQry qry);
    PageResponse<TaskGroupDTO> page(TaskGroupPageQry userPageQry);
}
