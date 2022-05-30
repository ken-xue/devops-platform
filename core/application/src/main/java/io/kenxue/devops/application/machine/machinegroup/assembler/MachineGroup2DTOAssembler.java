package io.kenxue.devops.application.machine.machinegroup.assembler;

import io.kenxue.devops.application.application.machine.machinegroup.assembler.MachineGroup2DTOMapStruct;
import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.MachineGroupDTO;
import io.kenxue.devops.domain.domain.machine.MachineGroup;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroup2DTOAssembler implements Assembler<MachineGroupDTO, MachineGroup> {

    @Override
    public MachineGroupDTO toDTO(MachineGroup machineGroup) {
        return MachineGroup2DTOMapStruct.INSTANCE.toDTO(machineGroup);
    }

    @Override
    public MachineGroup toDomain(MachineGroupDTO machineGroupDTO) {
        return MachineGroup2DTOMapStruct.INSTANCE.toDomain(machineGroupDTO);
    }

    @Override
    public List<MachineGroupDTO> toDTOList(List<MachineGroup> machineGroupList) {
        return MachineGroup2DTOMapStruct.INSTANCE.toDTOList(machineGroupList);
    }

    @Override
    public List<MachineGroup> toDomainList(List<MachineGroupDTO> machineGroupDTOList) {
        return MachineGroup2DTOMapStruct.INSTANCE.toDomainList(machineGroupDTOList);
    }

    @Override
    public Page<MachineGroupDTO> toDTOPage(Page<MachineGroup> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), MachineGroup2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
