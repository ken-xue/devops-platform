package io.kenxue.devops.application.machine.machinegroup.command.query;

import io.kenxue.devops.application.machine.machinegroup.assembler.MachineGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.MachineGroupDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.MachineGroupGetQry;
import io.kenxue.devops.domain.repository.machine.MachineGroupRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroupGetQryExe {

    @Resource
    private MachineGroupRepository machineGroupRepository;
    @Resource
    private MachineGroup2DTOAssembler machineGroup2DTOAssembler;

    public SingleResponse<MachineGroupDTO> execute(MachineGroupGetQry qry) {
        return SingleResponse.of(machineGroup2DTOAssembler.toDTO(machineGroupRepository.getById(qry.getId())));
    }

}
