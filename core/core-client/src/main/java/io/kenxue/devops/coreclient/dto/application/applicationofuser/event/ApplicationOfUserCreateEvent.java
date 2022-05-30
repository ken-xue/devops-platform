package io.kenxue.devops.coreclient.dto.application.applicationofuser.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@AllArgsConstructor
public class ApplicationOfUserCreateEvent implements DomainEventI {
    private String uuid;
}
