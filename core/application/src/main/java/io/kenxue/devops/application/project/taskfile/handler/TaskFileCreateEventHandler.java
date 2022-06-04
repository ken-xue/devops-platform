package io.kenxue.devops.application.project.taskfile.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskfile.event.TaskFileCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Slf4j
@EventHandler
public class TaskFileCreateEventHandler implements EventHandlerI<Response, TaskFileCreateEvent> {

    public Response execute(TaskFileCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
