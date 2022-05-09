package io.kenxue.cicd.application.application.machine.machineofgroup.command.query;

import io.kenxue.cicd.application.application.machine.machineofgroup.assembler.MachineOfGroup2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupGetQry;
import io.kenxue.cicd.domain.repository.machine.MachineOfGroupRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
