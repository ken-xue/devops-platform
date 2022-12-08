package io.kenxue.devops.application.machine.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.devops.application.pipeline.pipeline.manager.BuilderMachineManager;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.event.BuilderMachineActiveEvent;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Resource;

/**
 * @author 麦奇
 */
@Slf4j
@EventHandler
public class BuilderMachineActiveEventHandler implements EventHandlerI<Response, BuilderMachineActiveEvent> {

    @Resource
    private BuilderMachineManager builderMachineManager;
    @Resource
    private MachineInfo2DTOAssembler machineInfo2DTOAssembler;

    @Override
    public Response execute(BuilderMachineActiveEvent event) {
        log.debug("Handling Event:{}",event);
        builderMachineManager.add(event.getMachineInfoDTO().getIp(),machineInfo2DTOAssembler.toDomain(event.getMachineInfoDTO()));
        log.info("add cached builder machine : {}",event.getMachineInfoDTO());
        return Response.success();
    }
}
