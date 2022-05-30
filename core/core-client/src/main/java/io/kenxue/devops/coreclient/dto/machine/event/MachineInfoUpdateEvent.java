package io.kenxue.devops.coreclient.dto.machine.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Data
@AllArgsConstructor
public class MachineInfoUpdateEvent implements DomainEventI {
    private String uuid;
}
