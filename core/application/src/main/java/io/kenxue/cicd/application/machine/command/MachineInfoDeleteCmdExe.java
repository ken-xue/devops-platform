package io.kenxue.cicd.application.machine.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.application.MachineInfoRepository;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
