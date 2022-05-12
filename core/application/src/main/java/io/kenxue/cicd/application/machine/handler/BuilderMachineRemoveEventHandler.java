package io.kenxue.cicd.application.machine.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.application.pipeline.pipeline.manager.BuilderMachineManager;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.machine.event.BuilderMachineRemoveEvent;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author 麦奇
 */
@Slf4j
@EventHandler
public class BuilderMachineRemoveEventHandler implements EventHandlerI<Response, BuilderMachineRemoveEvent> {

    @Resource
    private BuilderMachineManager builderMachineManager;

    @Override
    public Response execute(BuilderMachineRemoveEvent event) {
        log.debug("Handling Event:{}",event);
        MachineInfo remove = builderMachineManager.remove(event.getMachineInfoDTO().getIp());
        log.info("remove builder machine cached : {}",remove);
        return Response.success();
    }
}
