package io.kenxue.devops.coreclient.dto.sys.menu;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Data
public class MenuUpdateCmd extends CommonCommand {

    @NotNull
    private MenuDTO menuDTO;
}
