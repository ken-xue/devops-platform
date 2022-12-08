package io.kenxue.devops.coreclient.dto.machine.machinegroup;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Data
@Accessors(chain = true)
public class MachineGroupDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
