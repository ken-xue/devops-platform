package io.kenxue.devops.application.machine.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.event.MachineInfoUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Slf4j
@EventHandler
public class MachineInfoUpdateEventHandler implements EventHandlerI<Response, MachineInfoUpdateEvent> {
    
    public Response execute(MachineInfoUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
