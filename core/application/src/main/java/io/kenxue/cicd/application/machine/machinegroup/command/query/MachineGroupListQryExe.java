package io.kenxue.cicd.application.application.machine.machinegroup.command.query;

import io.kenxue.cicd.application.application.machine.machinegroup.assembler.MachineGroup2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupDTO;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupListQry;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import io.kenxue.cicd.domain.repository.machine.MachineGroupRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroupListQryExe {
    @Resource
    private MachineGroupRepository machineGroupRepository;
    @Resource
    private MachineGroup2DTOAssembler machineGroup2DTOAssembler;

    public MultiResponse<MachineGroupDTO> execute(MachineGroupListQry qry) {
        List<MachineGroup> machineGroupDTOList = machineGroupRepository.list(qry);
        return MultiResponse.of(machineGroup2DTOAssembler.toDTOList(machineGroupDTOList));
    }
}
