package io.kenxue.devops.coreclient.dto.sys.userofrole;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Data
@Accessors(chain = true)
public class UserOfRolePageQry extends PageQuery {

    private UserOfRoleDTO userOfRoleDTO;

}
