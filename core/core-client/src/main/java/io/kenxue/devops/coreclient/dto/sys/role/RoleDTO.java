package io.kenxue.devops.coreclient.dto.sys.role;


import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Data
@Accessors(chain = true)
public class RoleDTO extends CommonDTO {
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 备注
     */
    private String roleRemark;
    /**
     * 关联的菜单
     */
    private List<String> menuList;
}
