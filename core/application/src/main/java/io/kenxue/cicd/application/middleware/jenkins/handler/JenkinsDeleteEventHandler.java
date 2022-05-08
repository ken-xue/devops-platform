package io.kenxue.cicd.application.middleware.jenkins.handler;


import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.jenkins.jenkins.event.JenkinsDeleteEvent;
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
