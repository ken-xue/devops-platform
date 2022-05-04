package io.kenxue.cicd.application.application.appinfo.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.event.ApplicationInfoUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@EventHandler
public class ApplicationInfoUpdateEventHandler implements EventHandlerI<Response, ApplicationInfoUpdateEvent> {
    
    public Response execute(ApplicationInfoUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
