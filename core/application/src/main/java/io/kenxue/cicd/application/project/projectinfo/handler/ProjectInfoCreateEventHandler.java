package io.kenxue.cicd.application.project.projectinfo.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.event.ProjectInfoCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Slf4j
@EventHandler
public class ProjectInfoCreateEventHandler implements EventHandlerI<Response, ProjectInfoCreateEvent> {

    public Response execute(ProjectInfoCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
