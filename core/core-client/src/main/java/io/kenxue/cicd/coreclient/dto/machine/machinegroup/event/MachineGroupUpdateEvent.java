package io.kenxue.cicd.coreclient.dto.machine.machinegroup.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Data
@AllArgsConstructor
public class MachineGroupUpdateEvent implements DomainEventI {
    private String uuid;
}
