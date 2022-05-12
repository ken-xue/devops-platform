package io.kenxue.cicd.coreclient.dto.machine.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 麦奇
 */
@Data
@AllArgsConstructor
public class BuilderMachineRemoveEvent implements DomainEventI {
    private MachineInfoDTO machineInfoDTO;
}
