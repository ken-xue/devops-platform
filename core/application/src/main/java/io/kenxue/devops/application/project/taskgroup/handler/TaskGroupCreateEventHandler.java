package io.kenxue.devops.application.project.taskgroup.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskgroup.event.TaskGroupCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Slf4j
@EventHandler
public class TaskGroupCreateEventHandler implements EventHandlerI<Response, TaskGroupCreateEvent> {

    public Response execute(TaskGroupCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
