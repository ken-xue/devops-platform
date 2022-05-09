package io.kenxue.cicd.application.application.machine.machineofgroup.assembler;

import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDTO;
import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MachineOfGroup2DTOMapStruct {

    MachineOfGroup2DTOMapStruct INSTANCE = Mappers.getMapper(MachineOfGroup2DTOMapStruct.class);

    @Mappings({})
    MachineOfGroupDTO toDTO(MachineOfGroup machineOfGroup);

    @Mappings({})
    MachineOfGroup toDomain(MachineOfGroupDTO machineOfGroupDTO);

    List<MachineOfGroupDTO> toDTOList(List<MachineOfGroup> MachineOfGroupList);

    List<MachineOfGroup> toDomainList(List<MachineOfGroupDTO> MachineOfGroupDTOList);
}
