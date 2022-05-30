package io.kenxue.devops.application.machine.machinegroup.handler;


import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.event.MachineGroupDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Slf4j
@EventHandler
public class MachineGroupDeleteEventHandler implements EventHandlerI<Response, MachineGroupDeleteEvent> {
    
    public Response execute(MachineGroupDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
