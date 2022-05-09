package io.kenxue.cicd.coreclient.dto.machine.machinegroup;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Data
@Accessors(chain = true)
public class MachineGroupAddCmd extends CommonCommand {
    @NotNull
    private MachineGroupDTO machineGroupDTO;
}
