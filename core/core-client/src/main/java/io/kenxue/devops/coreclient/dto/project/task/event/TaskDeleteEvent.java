package io.kenxue.devops.coreclient.dto.project.task.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Data
@AllArgsConstructor
public class TaskDeleteEvent implements DomainEventI {
    private String uuid;
}
