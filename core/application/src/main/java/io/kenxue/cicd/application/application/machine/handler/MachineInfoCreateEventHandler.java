package io.kenxue.cicd.application.application.machine.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.event.MachineInfoCreateEvent;
import lombok.extern.slf4j.Slf4j;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Slf4j
@EventHandler
public class MachineInfoCreateEventHandler implements EventHandlerI<Response, MachineInfoCreateEvent> {

    public Response execute(MachineInfoCreateEvent event) {
        log.debug("Handling Event:{}",event);
        return Response.success();
    }
}
