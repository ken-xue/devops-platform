package io.kenxue.devops.application.pipeline.node.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.event.PipelineNodeUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Slf4j
@EventHandler
public class PipelineNodeUpdateEventHandler implements EventHandlerI<Response, PipelineNodeUpdateEvent> {
    
    public Response execute(PipelineNodeUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
