package io.kenxue.devops.coreclient.dto.sys.applicationofuser.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@AllArgsConstructor
public class ApplicationOfUserUpdateEvent implements DomainEventI {
    private String uuid;
}
