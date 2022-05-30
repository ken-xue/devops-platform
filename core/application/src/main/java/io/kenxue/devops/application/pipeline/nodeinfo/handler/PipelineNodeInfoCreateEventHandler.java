package io.kenxue.devops.application.pipeline.nodeinfo.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.event.PipelineNodeInfoCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Slf4j
@EventHandler
public class PipelineNodeInfoCreateEventHandler implements EventHandlerI<Response, PipelineNodeInfoCreateEvent> {

    public Response execute(PipelineNodeInfoCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
