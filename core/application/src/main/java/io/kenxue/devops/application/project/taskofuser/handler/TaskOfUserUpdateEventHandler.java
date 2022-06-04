package io.kenxue.devops.application.project.taskofuser.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskofuser.event.TaskOfUserUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Slf4j
@EventHandler
public class TaskOfUserUpdateEventHandler implements EventHandlerI<Response, TaskOfUserUpdateEvent> {
    
    public Response execute(TaskOfUserUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
