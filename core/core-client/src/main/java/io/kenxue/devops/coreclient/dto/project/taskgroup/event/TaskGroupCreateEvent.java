package io.kenxue.devops.coreclient.dto.project.taskgroup.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Data
@AllArgsConstructor
public class TaskGroupCreateEvent implements DomainEventI {
    private String uuid;
}
