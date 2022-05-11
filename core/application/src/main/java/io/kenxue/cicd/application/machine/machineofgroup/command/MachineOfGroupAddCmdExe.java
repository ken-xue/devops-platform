package io.kenxue.cicd.application.machine.machineofgroup.command;

import io.kenxue.cicd.application.application.machine.machineofgroup.assembler.MachineOfGroup2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.machine.MachineOfGroupRepository;
import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroupAddCmdExe {

    @Resource
    private MachineOfGroupRepository machineOfGroupRepository;
    @Resource
    private MachineOfGroup2DTOAssembler machineOfGroup2DTOAssembler;

    public Response execute(MachineOfGroupAddCmd cmd) {
        MachineOfGroup machineOfGroup = machineOfGroup2DTOAssembler.toDomain(cmd.getMachineOfGroupDTO());
        machineOfGroup.create(UserThreadContext.get());
        machineOfGroupRepository.create(machineOfGroup);
        return Response.success();
    }
}
