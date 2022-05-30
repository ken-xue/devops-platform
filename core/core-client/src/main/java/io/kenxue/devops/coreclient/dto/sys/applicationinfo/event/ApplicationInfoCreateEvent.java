package io.kenxue.devops.coreclient.dto.sys.applicationinfo.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 应用
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@AllArgsConstructor
public class ApplicationInfoCreateEvent implements DomainEventI {
    private String uuid;
}
