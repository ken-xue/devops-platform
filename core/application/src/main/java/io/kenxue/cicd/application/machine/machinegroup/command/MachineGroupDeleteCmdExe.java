package io.kenxue.cicd.application.application.machine.machinegroup.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.machine.MachineGroupRepository;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroupDeleteCmdExe {
    @Resource
    private MachineGroupRepository machineGroupRepository;

    public Response execute(MachineGroupDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            MachineGroup machineGroup =machineGroupRepository.getById(id);
            machineGroup.deleted(UserThreadContext.get());
            machineGroupRepository.update(machineGroup);
        }
        return Response.success();
    }
}
