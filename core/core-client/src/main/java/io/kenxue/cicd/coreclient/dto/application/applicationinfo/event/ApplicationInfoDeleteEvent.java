package io.kenxue.cicd.coreclient.dto.application.applicationinfo.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@AllArgsConstructor
public class ApplicationInfoDeleteEvent implements DomainEventI {
    private String uuid;
}