package io.kenxue.devops.application.pipeline.logger.pipeline.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.event.PipelineExecuteLoggerUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Slf4j
@EventHandler
public class PipelineExecuteLoggerUpdateEventHandler implements EventHandlerI<Response, PipelineExecuteLoggerUpdateEvent> {
    
    public Response execute(PipelineExecuteLoggerUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
