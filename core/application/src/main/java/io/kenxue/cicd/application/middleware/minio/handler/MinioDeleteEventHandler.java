package io.kenxue.cicd.application.middleware.minio.handler;


import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.minio.event.MinioDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Slf4j
@EventHandler
public class MinioDeleteEventHandler implements EventHandlerI<Response, MinioDeleteEvent> {
    
    public Response execute(MinioDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
