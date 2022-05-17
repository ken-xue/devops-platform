package io.kenxue.cicd.application.middleware.zookeeper.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.event.ZookeeperCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Slf4j
@EventHandler
public class ZookeeperCreateEventHandler implements EventHandlerI<Response, ZookeeperCreateEvent> {

    public Response execute(ZookeeperCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
