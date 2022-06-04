package io.kenxue.devops.application.project.taskoperatorlogger.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.event.TaskOperatorLoggerUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Slf4j
@EventHandler
public class TaskOperatorLoggerUpdateEventHandler implements EventHandlerI<Response, TaskOperatorLoggerUpdateEvent> {
    
    public Response execute(TaskOperatorLoggerUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
