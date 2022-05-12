package io.kenxue.cicd.application.machine.machinegroup.command;

import io.kenxue.cicd.application.machine.machinegroup.assembler.MachineGroup2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.machine.MachineGroupRepository;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroupAddCmdExe {

    @Resource
    private MachineGroupRepository machineGroupRepository;
    @Resource
    private MachineGroup2DTOAssembler machineGroup2DTOAssembler;

    public Response execute(MachineGroupAddCmd cmd) {
        MachineGroup machineGroup = machineGroup2DTOAssembler.toDomain(cmd.getMachineGroupDTO());
        machineGroup.create(UserThreadContext.get());
        machineGroupRepository.create(machineGroup);
        return Response.success();
    }
}
