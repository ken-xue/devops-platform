package io.kenxue.cicd.application.pipeline.pipeline.handler;


import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.event.ApplicationPipelineDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@EventHandler
public class ApplicationPipelineDeleteEventHandler implements EventHandlerI<Response, ApplicationPipelineDeleteEvent> {
    
    public Response execute(ApplicationPipelineDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
