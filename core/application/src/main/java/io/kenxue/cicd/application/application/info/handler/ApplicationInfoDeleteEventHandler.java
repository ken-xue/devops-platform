package io.kenxue.cicd.application.application.info.handler;


import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.event.ApplicationInfoDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@EventHandler
public class ApplicationInfoDeleteEventHandler implements EventHandlerI<Response, ApplicationInfoDeleteEvent> {
    
    public Response execute(ApplicationInfoDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
