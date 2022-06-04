package io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@AllArgsConstructor
public class TaskOperatorLoggerCreateEvent implements DomainEventI {
    private String uuid;
}
