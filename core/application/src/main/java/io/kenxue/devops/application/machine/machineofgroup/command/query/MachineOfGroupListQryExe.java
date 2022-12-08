package io.kenxue.devops.application.machine.machineofgroup.command.query;

import io.kenxue.devops.application.machine.machineofgroup.assembler.MachineOfGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupListQry;
import io.kenxue.devops.domain.domain.machine.MachineOfGroup;
import io.kenxue.devops.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroupListQryExe {
    @Resource
    private MachineOfGroupRepository machineOfGroupRepository;
    @Resource
    private MachineOfGroup2DTOAssembler machineOfGroup2DTOAssembler;

    public MultiResponse<MachineOfGroupDTO> execute(MachineOfGroupListQry qry) {
        List<MachineOfGroup> machineOfGroupDTOList = machineOfGroupRepository.list(qry);
        return MultiResponse.of(machineOfGroup2DTOAssembler.toDTOList(machineOfGroupDTOList));
    }
}
