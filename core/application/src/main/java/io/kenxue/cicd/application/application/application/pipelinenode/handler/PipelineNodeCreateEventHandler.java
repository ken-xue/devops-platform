package io.kenxue.cicd.application.application.application.pipelinenode.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.pipelinenode.event.PipelineNodeCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Slf4j
@EventHandler
public class PipelineNodeCreateEventHandler implements EventHandlerI<Response, PipelineNodeCreateEvent> {

    public Response execute(PipelineNodeCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
