package io.kenxue.cicd.application.kubernetes.cluster.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.event.ClusterCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Slf4j
@EventHandler
public class ClusterCreateEventHandler implements EventHandlerI<Response, ClusterCreateEvent> {

    public Response execute(ClusterCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
