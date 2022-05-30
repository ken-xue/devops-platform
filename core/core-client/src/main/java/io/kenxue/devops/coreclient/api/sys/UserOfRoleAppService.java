package io.kenxue.devops.coreclient.api.sys;


import io.kenxue.devops.coreclient.dto.sys.userofrole.*;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;


/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
public interface UserOfRoleAppService {
    Response add(UserOfRoleAddCmd cmd);
    Response update(UserOfRoleUpdateCmd cmd);
    Response delete(UserOfRoleDeleteCmd userDeleteCmd);
    SingleResponse<UserOfRoleDTO> getById(UserOfRoleGetQry qry);
    MultiResponse<UserOfRoleDTO> list(UserOfRoleListQry qry);
    PageResponse<UserOfRoleDTO> page(UserOfRolePageQry userPageQry);
}
