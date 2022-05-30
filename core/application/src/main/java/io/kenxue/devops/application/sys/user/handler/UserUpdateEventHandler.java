package io.kenxue.devops.application.sys.user.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.user.event.UserUpdateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventHandler
public class UserUpdateEventHandler implements EventHandlerI<Response, UserUpdateEvent> {
    
    public Response execute(UserUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
