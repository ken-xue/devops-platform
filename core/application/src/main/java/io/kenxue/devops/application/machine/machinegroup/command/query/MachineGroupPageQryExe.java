package io.kenxue.devops.application.machine.machinegroup.command.query;

import io.kenxue.devops.application.machine.machinegroup.assembler.MachineGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.MachineGroupDTO;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.MachineGroupPageQry;
import io.kenxue.devops.domain.domain.machine.MachineGroup;
import io.kenxue.devops.domain.repository.machine.MachineGroupRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroupPageQryExe {

    @Resource
    private MachineGroupRepository machineGroupRepository;
    @Resource
    private MachineGroup2DTOAssembler machineGroup2DTOAssembler;

    public PageResponse<MachineGroupDTO> execute(MachineGroupPageQry qry) {
        Page<MachineGroup> page = machineGroupRepository.page(qry);
        return PageResponse.of(machineGroup2DTOAssembler.toDTOPage(page));
    }
}
