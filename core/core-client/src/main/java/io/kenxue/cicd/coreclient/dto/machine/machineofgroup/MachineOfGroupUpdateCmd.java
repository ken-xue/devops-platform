package io.kenxue.cicd.coreclient.dto.machine.machineofgroup;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Data
@Accessors(chain = true)
public class MachineOfGroupUpdateCmd extends CommonCommand {

    @NotNull
    private MachineOfGroupDTO machineOfGroupDTO;
}
