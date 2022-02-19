package io.kenxue.cicd.coreclient.dto.project.projectinfo.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@AllArgsConstructor
public class ProjectInfoDeleteEvent implements DomainEventI {
    private String uuid;
}
