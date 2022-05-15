package io.kenxue.cicd.application.kubernetes.clusternode.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.event.ClusterNodeUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Slf4j
@EventHandler
public class ClusterNodeUpdateEventHandler implements EventHandlerI<Response, ClusterNodeUpdateEvent> {
    
    public Response execute(ClusterNodeUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}