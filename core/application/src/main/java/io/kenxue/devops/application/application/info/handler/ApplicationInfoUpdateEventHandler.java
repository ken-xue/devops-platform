package io.kenxue.devops.application.application.info.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.event.ApplicationInfoUpdateEvent;
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
