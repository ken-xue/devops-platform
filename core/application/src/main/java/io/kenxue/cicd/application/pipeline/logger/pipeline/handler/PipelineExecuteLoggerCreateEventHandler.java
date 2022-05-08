package io.kenxue.cicd.application.pipeline.logger.pipeline.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.event.PipelineExecuteLoggerCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Slf4j
@EventHandler
public class PipelineExecuteLoggerCreateEventHandler implements EventHandlerI<Response, PipelineExecuteLoggerCreateEvent> {

    public Response execute(PipelineExecuteLoggerCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
