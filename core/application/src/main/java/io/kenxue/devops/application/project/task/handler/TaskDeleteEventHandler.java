package io.kenxue.devops.application.project.task.handler;


import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.task.event.TaskDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Slf4j
@EventHandler
public class TaskDeleteEventHandler implements EventHandlerI<Response, TaskDeleteEvent> {
    
    public Response execute(TaskDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
