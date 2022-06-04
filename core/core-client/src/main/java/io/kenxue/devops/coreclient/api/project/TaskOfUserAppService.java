package io.kenxue.devops.coreclient.api.project;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskofuser.*;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public interface TaskOfUserAppService {
    Response add(TaskOfUserAddCmd cmd);
    Response update(TaskOfUserUpdateCmd cmd);
    Response delete(TaskOfUserDeleteCmd userDeleteCmd);
    SingleResponse<TaskOfUserDTO> getById(TaskOfUserGetQry qry);
    MultiResponse<TaskOfUserDTO> list(TaskOfUserListQry qry);
    PageResponse<TaskOfUserDTO> page(TaskOfUserPageQry userPageQry);
}
