package io.kenxue.devops.application.middleware.minio.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.minio.event.MinioUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Slf4j
@EventHandler
public class MinioUpdateEventHandler implements EventHandlerI<Response, MinioUpdateEvent> {
    
    public Response execute(MinioUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
