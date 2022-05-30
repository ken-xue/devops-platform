package io.kenxue.devops.coreclient.dto.machine.machineofgroup;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Data
@Accessors(chain = true)
public class MachineOfGroupAddCmd extends CommonCommand {
    @NotNull
    private MachineOfGroupDTO machineOfGroupDTO;
}
