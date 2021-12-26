package io.kenxue.cicd.coreclient.dto.application.applicationofuser.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@AllArgsConstructor
public class ApplicationOfUserCreateEvent implements DomainEventI {
    private String uuid;
}
