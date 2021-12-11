package io.kenxue.cicd.coreclient.dto.sys.user.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 删除用户领域事件
 */
@Data
@AllArgsConstructor
public class UserCreateEvent implements DomainEventI {
    private String userUuid;
}
