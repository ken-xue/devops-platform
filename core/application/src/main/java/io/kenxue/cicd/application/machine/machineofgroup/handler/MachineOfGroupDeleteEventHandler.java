package io.kenxue.cicd.application.application.machine.machineofgroup.handler;


import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.event.MachineOfGroupDeleteEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Slf4j
@EventHandler
public class MachineOfGroupDeleteEventHandler implements EventHandlerI<Response, MachineOfGroupDeleteEvent> {
    
    public Response execute(MachineOfGroupDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
