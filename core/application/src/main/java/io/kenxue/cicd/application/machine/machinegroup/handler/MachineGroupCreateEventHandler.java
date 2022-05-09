package io.kenxue.cicd.application.application.machine.machinegroup.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.event.MachineGroupCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Slf4j
@EventHandler
public class MachineGroupCreateEventHandler implements EventHandlerI<Response, MachineGroupCreateEvent> {

    public Response execute(MachineGroupCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
