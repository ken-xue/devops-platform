package io.kenxue.cicd.coreclient.dto.machine;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
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
