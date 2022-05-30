package io.kenxue.devops.application.kubernetes.cluster.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.event.ClusterCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Slf4j
@EventHandler
public class ClusterCreateEventHandler implements EventHandlerI<Response, ClusterCreateEvent> {

    public Response execute(ClusterCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
