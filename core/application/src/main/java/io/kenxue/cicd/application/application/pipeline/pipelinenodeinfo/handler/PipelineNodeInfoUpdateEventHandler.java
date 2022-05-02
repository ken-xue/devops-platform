package io.kenxue.cicd.application.application.pipeline.pipelinenodeinfo.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.event.PipelineNodeInfoUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Slf4j
@EventHandler
public class PipelineNodeInfoUpdateEventHandler implements EventHandlerI<Response, PipelineNodeInfoUpdateEvent> {
    
    public Response execute(PipelineNodeInfoUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}