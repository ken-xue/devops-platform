package io.kenxue.cicd.application.kubernetes.clusternode.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.event.ClusterNodeCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Slf4j
@EventHandler
public class ClusterNodeCreateEventHandler implements EventHandlerI<Response, ClusterNodeCreateEvent> {

    public Response execute(ClusterNodeCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
