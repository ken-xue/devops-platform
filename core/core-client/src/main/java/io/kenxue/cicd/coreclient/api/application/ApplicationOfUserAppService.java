package io.kenxue.cicd.coreclient.api.application;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.*;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationOfUserAppService {
    Response add(ApplicationOfUserAddCmd cmd);
    Response update(ApplicationOfUserUpdateCmd cmd);
    Response delete(ApplicationOfUserDeleteCmd userDeleteCmd);
    SingleResponse<ApplicationOfUserDTO> getById(ApplicationOfUserGetQry qry);
    MultiResponse<ApplicationOfUserDTO> list(ApplicationOfUserListQry qry);
    PageResponse<ApplicationOfUserDTO> page(ApplicationOfUserPageQry userPageQry);
}
