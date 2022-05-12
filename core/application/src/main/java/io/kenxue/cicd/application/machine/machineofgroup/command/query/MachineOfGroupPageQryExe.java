package io.kenxue.cicd.application.machine.machineofgroup.command.query;

import io.kenxue.cicd.application.machine.machineofgroup.assembler.MachineOfGroup2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupPageQry;
import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import io.kenxue.cicd.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
