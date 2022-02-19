package io.kenxue.cicd.coreclient.dto.project.projectofuser.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@AllArgsConstructor
public class ProjectOfUserDeleteEvent implements DomainEventI {
    private String uuid;
}
