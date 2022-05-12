package io.kenxue.cicd.application.machine.machineofgroup.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.application.machine.machineofgroup.assembler.MachineOfGroup2DTOMapStruct;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroup2DTOAssembler implements Assembler<MachineOfGroupDTO, MachineOfGroup> {

    @Override
    public MachineOfGroupDTO toDTO(MachineOfGroup machineOfGroup) {
        return MachineOfGroup2DTOMapStruct.INSTANCE.toDTO(machineOfGroup);
    }

    @Override
    public MachineOfGroup toDomain(MachineOfGroupDTO machineOfGroupDTO) {
        return MachineOfGroup2DTOMapStruct.INSTANCE.toDomain(machineOfGroupDTO);
    }

    @Override
    public List<MachineOfGroupDTO> toDTOList(List<MachineOfGroup> machineOfGroupList) {
        return MachineOfGroup2DTOMapStruct.INSTANCE.toDTOList(machineOfGroupList);
    }

    @Override
    public List<MachineOfGroup> toDomainList(List<MachineOfGroupDTO> machineOfGroupDTOList) {
        return MachineOfGroup2DTOMapStruct.INSTANCE.toDomainList(machineOfGroupDTOList);
    }

    @Override
    public Page<MachineOfGroupDTO> toDTOPage(Page<MachineOfGroup> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), MachineOfGroup2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
