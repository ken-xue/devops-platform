package io.kenxue.devops.application.kubernetes.cluster.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.event.ClusterUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Slf4j
@EventHandler
public class ClusterUpdateEventHandler implements EventHandlerI<Response, ClusterUpdateEvent> {
    
    public Response execute(ClusterUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
