package io.kenxue.cicd.application.application.machine.machinegroup.command.query;

import io.kenxue.cicd.application.application.machine.machinegroup.assembler.MachineGroup2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupDTO;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupPageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.machine.MachineGroupRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
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
