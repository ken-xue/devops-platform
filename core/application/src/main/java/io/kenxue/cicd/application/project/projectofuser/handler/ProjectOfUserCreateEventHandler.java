package io.kenxue.cicd.application.project.projectofuser.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.event.ProjectOfUserCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Slf4j
@EventHandler
public class ProjectOfUserCreateEventHandler implements EventHandlerI<Response, ProjectOfUserCreateEvent> {

    public Response execute(ProjectOfUserCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
