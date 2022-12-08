package io.kenxue.devops.coreclient.dto.sys.roleofmenu;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Data
@Accessors(chain = true)
public class RoleOfMenuAddCmd extends CommonCommand {
    @NotNull
    private RoleOfMenuDTO roleOfMenuDTO;
}
