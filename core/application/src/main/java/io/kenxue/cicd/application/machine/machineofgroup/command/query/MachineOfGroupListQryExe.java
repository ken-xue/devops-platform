package io.kenxue.cicd.application.machine.machineofgroup.command.query;

import io.kenxue.cicd.application.machine.machineofgroup.assembler.MachineOfGroup2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupListQry;
import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import io.kenxue.cicd.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
