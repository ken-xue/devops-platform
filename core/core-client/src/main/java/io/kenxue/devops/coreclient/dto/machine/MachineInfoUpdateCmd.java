package io.kenxue.devops.coreclient.dto.machine;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Data
@Accessors(chain = true)
public class MachineInfoUpdateCmd extends CommonCommand {

    @NotNull
    private MachineInfoDTO machineInfoDTO;
}
