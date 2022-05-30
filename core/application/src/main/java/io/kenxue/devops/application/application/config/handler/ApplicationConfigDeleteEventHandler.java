package io.kenxue.devops.application.application.config.handler;


import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.event.ApplicationConfigDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@EventHandler
public class ApplicationConfigDeleteEventHandler implements EventHandlerI<Response, ApplicationConfigDeleteEvent> {
    
    public Response execute(ApplicationConfigDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
