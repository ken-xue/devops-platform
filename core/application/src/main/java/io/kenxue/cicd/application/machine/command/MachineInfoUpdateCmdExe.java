package io.kenxue.cicd.application.machine.command;

import io.kenxue.cicd.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.api.machine.MachineOfGroupAppService;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupAddCmd;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupAddCmd;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDeleteCmd;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoUpdateCmd;
import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import io.kenxue.cicd.domain.factory.machine.MachineOfGroupFactory;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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

    public Response execute(MachineInfoUpdateCmd cmd) {
        MachineInfo machineInfo = machineInfo2DTOAssembler.toDomain(cmd.getMachineInfoDTO());
        machineInfoRepository.update(machineInfo);
        machineOfGroupAppService.delete(new MachineOfGroupDeleteCmd().setMachineUuid(machineInfo.getUuid()));
        List<String> groupList = Optional.ofNullable(cmd.getMachineInfoDTO()).map(v->v.getGroupList()).orElse(new ArrayList<>());
        groupList.forEach(uuid-> machineOfGroupAppService.add(new MachineOfGroupAddCmd().setMachineOfGroupDTO(new MachineOfGroupDTO().setGroupUuid(uuid).setMachineUuid(machineInfo.getUuid()))));
        return Response.success();
    }
}