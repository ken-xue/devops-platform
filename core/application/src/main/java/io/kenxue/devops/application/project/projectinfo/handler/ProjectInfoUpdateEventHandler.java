package io.kenxue.devops.application.project.projectinfo.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.projectinfo.event.ProjectInfoUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Slf4j
@EventHandler
public class ProjectInfoUpdateEventHandler implements EventHandlerI<Response, ProjectInfoUpdateEvent> {
    
    public Response execute(ProjectInfoUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
