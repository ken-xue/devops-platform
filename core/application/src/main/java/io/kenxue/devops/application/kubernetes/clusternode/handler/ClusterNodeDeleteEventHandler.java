package io.kenxue.devops.application.kubernetes.clusternode.handler;


import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.event.ClusterNodeDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Slf4j
@EventHandler
public class ClusterNodeDeleteEventHandler implements EventHandlerI<Response, ClusterNodeDeleteEvent> {
    
    public Response execute(ClusterNodeDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
