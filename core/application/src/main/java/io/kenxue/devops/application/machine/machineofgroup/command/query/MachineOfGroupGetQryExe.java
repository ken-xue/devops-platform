package io.kenxue.devops.application.machine.machineofgroup.command.query;

import io.kenxue.devops.application.machine.machineofgroup.assembler.MachineOfGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupGetQry;
import io.kenxue.devops.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroupGetQryExe {

    @Resource
    private MachineOfGroupRepository machineOfGroupRepository;
    @Resource
    private MachineOfGroup2DTOAssembler machineOfGroup2DTOAssembler;

    public SingleResponse<MachineOfGroupDTO> execute(MachineOfGroupGetQry qry) {
        return SingleResponse.of(machineOfGroup2DTOAssembler.toDTO(machineOfGroupRepository.getById(qry.getId())));
    }

}
