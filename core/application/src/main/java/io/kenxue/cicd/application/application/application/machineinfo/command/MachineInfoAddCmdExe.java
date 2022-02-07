package io.kenxue.cicd.application.application.application.machineinfo.command;

import io.kenxue.cicd.application.application.application.machineinfo.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.application.MachineInfoRepository;
import io.kenxue.cicd.domain.domain.application.MachineInfo;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
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

    public Response execute(MachineInfoAddCmd cmd) {
        MachineInfo machineInfo = machineInfo2DTOAssembler.toDomain(cmd.getMachineInfoDTO());
        machineInfo.create(UserThreadContext.get());
        machineInfoRepository.create(machineInfo);
        return Response.success();
    }
}
