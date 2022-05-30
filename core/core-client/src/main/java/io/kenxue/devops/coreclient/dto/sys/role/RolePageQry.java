package io.kenxue.devops.coreclient.dto.sys.role;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Data
public class RolePageQry extends PageQuery {

    private RoleDTO roleDTO;

}
