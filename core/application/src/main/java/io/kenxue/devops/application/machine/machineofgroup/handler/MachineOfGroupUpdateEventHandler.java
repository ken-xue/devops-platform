package io.kenxue.devops.application.machine.machineofgroup.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.event.MachineOfGroupUpdateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Slf4j
@EventHandler
public class MachineOfGroupUpdateEventHandler implements EventHandlerI<Response, MachineOfGroupUpdateEvent> {
    
    public Response execute(MachineOfGroupUpdateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
