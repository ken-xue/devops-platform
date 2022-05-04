package io.kenxue.cicd.application.sys.user.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.user.event.UserUpdateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventHandler
public class UserUpdateEventHandler implements EventHandlerI<Response, UserUpdateEvent> {
    
    public Response execute(UserUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
