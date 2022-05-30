package io.kenxue.devops.coreclient.dto.sys.user.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 删除用户领域事件
 */
@Data
@AllArgsConstructor
public class UserUpdateEvent implements DomainEventI {
    private String userUuid;
}
