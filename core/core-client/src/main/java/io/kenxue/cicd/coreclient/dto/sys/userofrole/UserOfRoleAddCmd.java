package io.kenxue.cicd.coreclient.dto.sys.userofrole;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Data
@Accessors(chain = true)
public class UserOfRoleAddCmd extends CommonCommand {
    @NotNull
    private UserOfRoleDTO userOfRoleDTO;
}
