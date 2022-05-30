package io.kenxue.devops.coreclient.dto.machine.machinegroup;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Data
@Accessors(chain = true)
public class MachineGroupUpdateCmd extends CommonCommand {

    @NotNull
    private MachineGroupDTO machineGroupDTO;
}
