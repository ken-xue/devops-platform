package io.kenxue.devops.application.middleware.jenkins.handler;


import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.event.JenkinsDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Slf4j
@EventHandler
public class JenkinsDeleteEventHandler implements EventHandlerI<Response, JenkinsDeleteEvent> {
    
    public Response execute(JenkinsDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
