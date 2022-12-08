package io.kenxue.devops.application.machine.machineofgroup.command.query;

import io.kenxue.devops.application.machine.machineofgroup.assembler.MachineOfGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupPageQry;
import io.kenxue.devops.domain.domain.machine.MachineOfGroup;
import io.kenxue.devops.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroupPageQryExe {

    @Resource
    private MachineOfGroupRepository machineOfGroupRepository;
    @Resource
    private MachineOfGroup2DTOAssembler machineOfGroup2DTOAssembler;

    public PageResponse<MachineOfGroupDTO> execute(MachineOfGroupPageQry qry) {
        Page<MachineOfGroup> page = machineOfGroupRepository.page(qry);
        return PageResponse.of(machineOfGroup2DTOAssembler.toDTOPage(page));
    }
}
