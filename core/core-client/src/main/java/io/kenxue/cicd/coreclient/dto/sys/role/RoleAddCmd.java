package io.kenxue.cicd.coreclient.dto.sys.role;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Data
public class RoleAddCmd extends CommonCommand {
    @NotNull
    private RoleDTO roleDTO;
}
