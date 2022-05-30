package io.kenxue.devops.coreclient.dto.machine.machineofgroup.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Data
@AllArgsConstructor
public class MachineOfGroupCreateEvent implements DomainEventI {
    private String uuid;
}
