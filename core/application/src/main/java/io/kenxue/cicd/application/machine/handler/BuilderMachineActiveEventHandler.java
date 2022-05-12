package io.kenxue.cicd.application.machine.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.application.pipeline.pipeline.manager.BuilderMachineManager;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.machine.event.BuilderMachineActiveEvent;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

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
