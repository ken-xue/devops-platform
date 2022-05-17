package io.kenxue.cicd.application.middleware.zookeeper.handler;


import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.event.ZookeeperDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Slf4j
@EventHandler
public class ZookeeperDeleteEventHandler implements EventHandlerI<Response, ZookeeperDeleteEvent> {
    
    public Response execute(ZookeeperDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
