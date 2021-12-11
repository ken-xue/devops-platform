package io.kenxue.cicd.coreclient.api.sys;


import io.kenxue.cicd.coreclient.dto.sys.role.*;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
public interface RoleAppService {
    Response add(RoleAddCmd cmd);
    Response update(RoleUpdateCmd cmd);
    Response delete(RoleDeleteCmd userDeleteCmd);
    SingleResponse<RoleDTO> getById(RoleGetQry qry);
    MultiResponse<RoleDTO> list(RoleListQry qry);
    PageResponse<RoleDTO> page(RolePageQry userPageQry);
}
