package io.kenxue.devops.coreclient.api.sys;


import io.kenxue.devops.coreclient.dto.sys.user.*;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;

/**
 * UserAppService
 */
public interface UserAppService {
    Response add(UserAddCmd cmd);
    Response update(UserUpdateCmd cmd);
    Response delete(UserDeleteCmd userDeleteCmd);
    SingleResponse<UserDTO> getById(UserGetQry qry);
    MultiResponse<UserDTO> list(UserListQry qry);
    PageResponse<UserDTO> page(UserPageQry userPageQry);
    //获取用户登陆信息(角色)
    Response getInfoByUserId(UserFullGetQry userFullGetQry);
}
