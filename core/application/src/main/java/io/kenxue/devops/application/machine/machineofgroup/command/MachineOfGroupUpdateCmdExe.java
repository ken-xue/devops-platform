package io.kenxue.devops.application.machine.machineofgroup.command;

import io.kenxue.devops.application.machine.machineofgroup.assembler.MachineOfGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupUpdateCmd;
import io.kenxue.devops.domain.domain.machine.MachineOfGroup;
import io.kenxue.devops.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroupUpdateCmdExe {

    @Resource
    private MachineOfGroupRepository machineOfGroupRepository;
    @Resource
    private MachineOfGroup2DTOAssembler machineOfGroup2DTOAssembler;

    public Response execute(MachineOfGroupUpdateCmd cmd) {
        MachineOfGroup machineOfGroup = machineOfGroup2DTOAssembler.toDomain(cmd.getMachineOfGroupDTO());
        machineOfGroupRepository.update(machineOfGroup);
        return Response.success();
    }
}