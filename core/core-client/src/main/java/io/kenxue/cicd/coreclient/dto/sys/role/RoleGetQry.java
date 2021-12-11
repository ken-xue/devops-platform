package io.kenxue.cicd.coreclient.dto.sys.role;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Data
public class RoleGetQry extends CommonCommand {

    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 备注
     */
    private String roleRemark;
}