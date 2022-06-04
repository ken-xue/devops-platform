package io.kenxue.devops.application.project.taskworkinghours.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.event.TaskWorkingHoursCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Slf4j
@EventHandler
public class TaskWorkingHoursCreateEventHandler implements EventHandlerI<Response, TaskWorkingHoursCreateEvent> {

    public Response execute(TaskWorkingHoursCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
