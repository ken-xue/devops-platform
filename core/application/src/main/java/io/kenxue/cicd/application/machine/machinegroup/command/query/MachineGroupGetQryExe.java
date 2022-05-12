package io.kenxue.cicd.application.machine.machinegroup.command.query;

import io.kenxue.cicd.application.machine.machinegroup.assembler.MachineGroup2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupGetQry;
import io.kenxue.cicd.domain.repository.machine.MachineGroupRepository;
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
