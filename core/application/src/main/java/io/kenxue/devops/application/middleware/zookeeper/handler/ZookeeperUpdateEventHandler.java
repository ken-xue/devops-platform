package io.kenxue.devops.application.middleware.zookeeper.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.event.ZookeeperUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Slf4j
@EventHandler
public class ZookeeperUpdateEventHandler implements EventHandlerI<Response, ZookeeperUpdateEvent> {
    
    public Response execute(ZookeeperUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
