package io.kenxue.devops.application.machine.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.machine.MachineInfoRepository;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineInfoDeleteCmdExe {
    @Resource
    private MachineInfoRepository machineInfoRepository;

    public Response execute(MachineInfoDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            MachineInfo machineInfo =machineInfoRepository.getById(id);
            machineInfo.deleted(UserThreadContext.get());
            machineInfoRepository.update(machineInfo);
        }
        return Response.success();
    }
}
