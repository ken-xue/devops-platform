package io.kenxue.devops.coreclient.dto.machine.machineofgroup;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Data
@Accessors(chain = true)
public class MachineOfGroupDeleteCmd extends CommonCommand {
    @NotNull
    private String machineUuid;
}
