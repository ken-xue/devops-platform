package io.kenxue.devops.application.sys.user.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.user.event.UserCreateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventHandler
public class UserCreateEventHandler implements EventHandlerI<Response, UserCreateEvent> {

    public Response execute(UserCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
