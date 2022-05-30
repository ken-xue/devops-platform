package io.kenxue.devops.application.application.appofuser.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.event.ApplicationOfUserUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@EventHandler
public class ApplicationOfUserUpdateEventHandler implements EventHandlerI<Response, ApplicationOfUserUpdateEvent> {
    
    public Response execute(ApplicationOfUserUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
