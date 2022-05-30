package io.kenxue.devops.application.machine.command;

import io.kenxue.devops.application.common.event.EventBusI;
import io.kenxue.devops.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.devops.coreclient.api.machine.MachineOfGroupAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoUpdateCmd;
import io.kenxue.devops.coreclient.dto.machine.event.BuilderMachineActiveEvent;
import io.kenxue.devops.coreclient.dto.machine.event.BuilderMachineRemoveEvent;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupAddCmd;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupDeleteCmd;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupListQry;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.domain.repository.machine.MachineInfoRepository;
import io.kenxue.devops.sharedataboject.machine.enums.GroupEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineInfoUpdateCmdExe {
    @Resource
    private MachineInfoRepository machineInfoRepository;
    @Resource
    private MachineInfo2DTOAssembler machineInfo2DTOAssembler;
    @Resource
    private MachineOfGroupAppService machineOfGroupAppService;
    @Resource
    private EventBusI eventBusI;

    private String groupUuid = GroupEnum.BUILDER_MACHINE.getUuid();

    public Response execute(MachineInfoUpdateCmd cmd) {
        MachineInfo machineInfo = machineInfo2DTOAssembler.toDomain(cmd.getMachineInfoDTO());
        machineInfoRepository.update(machineInfo);
        List<MachineOfGroupDTO> ofGroup = machineOfGroupAppService.list(new MachineOfGroupListQry().setGroupUuid(cmd.getMachineInfoDTO().getGroupUuid())).getData();
        Optional<Boolean> builder = Optional.ofNullable(ofGroup).map(v -> v.stream().anyMatch(of->of.getGroupUuid().equals(groupUuid)));
        //设置为非构建机器
        if (builder.get() && !cmd.getMachineInfoDTO().getGroupList().contains(groupUuid)) {
            eventBusI.asyncPublish(new BuilderMachineRemoveEvent(machineInfo2DTOAssembler.toDTO(machineInfo)));
        }
        //设置为构建服务器
        if (!builder.get() && cmd.getMachineInfoDTO().getGroupList().contains(groupUuid)){
            eventBusI.asyncPublish(new BuilderMachineActiveEvent(machineInfo2DTOAssembler.toDTO(machineInfo)));
        }
        machineOfGroupAppService.delete(new MachineOfGroupDeleteCmd().setMachineUuid(machineInfo.getUuid()));
        List<String> groupList = Optional.ofNullable(cmd.getMachineInfoDTO()).map(v->v.getGroupList()).orElse(new ArrayList<>());
        groupList.forEach(uuid-> machineOfGroupAppService.add(new MachineOfGroupAddCmd().setMachineOfGroupDTO(new MachineOfGroupDTO().setGroupUuid(uuid).setMachineUuid(machineInfo.getUuid()))));
        return Response.success();
    }
}