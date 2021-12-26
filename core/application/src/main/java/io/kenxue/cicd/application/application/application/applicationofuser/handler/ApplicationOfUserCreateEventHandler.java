package io.kenxue.cicd.application.application.application.applicationofuser.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.event.ApplicationOfUserCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Slf4j
@EventHandler
public class ApplicationOfUserCreateEventHandler implements EventHandlerI<Response, ApplicationOfUserCreateEvent> {

    public Response execute(ApplicationOfUserCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
