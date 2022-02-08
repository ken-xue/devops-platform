package io.kenxue.cicd.application.application.application.appofuser.handler;


import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.event.ApplicationOfUserDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@EventHandler
public class ApplicationOfUserDeleteEventHandler implements EventHandlerI<Response, ApplicationOfUserDeleteEvent> {
    
    public Response execute(ApplicationOfUserDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
