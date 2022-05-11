package io.kenxue.cicd.application.machine.command;

import io.kenxue.cicd.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.api.machine.MachineOfGroupAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupAddCmd;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import io.kenxue.cicd.domain.factory.machine.MachineGroupFactory;
import io.kenxue.cicd.domain.factory.machine.MachineOfGroupFactory;
import io.kenxue.cicd.domain.repository.machine.MachineGroupRepository;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoAddCmd;
import io.kenxue.cicd.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineInfoAddCmdExe {

    @Resource
    private MachineInfoRepository machineInfoRepository;
    @Resource
    private MachineInfo2DTOAssembler machineInfo2DTOAssembler;
    @Resource
    private MachineOfGroupRepository machineOfGroupRepository;
    @Resource
    private MachineOfGroupAppService machineOfGroupAppService;

    public Response execute(MachineInfoAddCmd cmd) {
        MachineInfo machineInfo = machineInfo2DTOAssembler.toDomain(cmd.getMachineInfoDTO());
        machineInfo.create(UserThreadContext.get());
        machineInfoRepository.create(machineInfo);
        List<String> groupList = Optional.ofNullable(cmd.getMachineInfoDTO()).map(v->v.getGroupList()).orElse(Collections.emptyList());
        groupList.forEach(uuid-> machineOfGroupAppService.add(new MachineOfGroupAddCmd().setMachineOfGroupDTO(new MachineOfGroupDTO().setGroupUuid(uuid).setMachineUuid(machineInfo.getUuid()))));
        return Response.success();
    }
}
