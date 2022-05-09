package io.kenxue.cicd.application.machine.command;

import io.kenxue.cicd.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoUpdateCmd;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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

    public Response execute(MachineInfoUpdateCmd cmd) {
        MachineInfo machineInfo = machineInfo2DTOAssembler.toDomain(cmd.getMachineInfoDTO());
        machineInfoRepository.update(machineInfo);
        return Response.success();
    }
}