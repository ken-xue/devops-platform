package io.kenxue.cicd.application.machine.machineofgroup.command;

import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDeleteCmd;
import io.kenxue.cicd.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroupDeleteCmdExe {
    @Resource
    private MachineOfGroupRepository machineOfGroupRepository;

    public Response execute(MachineOfGroupDeleteCmd cmd) {
        machineOfGroupRepository.delete(cmd);
        return Response.success();
    }
}
