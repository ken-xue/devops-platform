package io.kenxue.cicd.coreclient.dto.application.applicationconfig.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@AllArgsConstructor
public class ApplicationConfigCreateEvent implements DomainEventI {
    private String uuid;
}
