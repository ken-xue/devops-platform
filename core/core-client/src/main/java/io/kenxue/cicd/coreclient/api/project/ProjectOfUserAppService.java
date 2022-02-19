package io.kenxue.cicd.coreclient.api.project;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.*;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
public interface ProjectOfUserAppService {
    Response add(ProjectOfUserAddCmd cmd);
    Response update(ProjectOfUserUpdateCmd cmd);
    Response delete(ProjectOfUserDeleteCmd userDeleteCmd);
    SingleResponse<ProjectOfUserDTO> getById(ProjectOfUserGetQry qry);
    MultiResponse<ProjectOfUserDTO> list(ProjectOfUserListQry qry);
    PageResponse<ProjectOfUserDTO> page(ProjectOfUserPageQry userPageQry);
}
