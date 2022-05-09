package io.kenxue.cicd.application.application.machine.machinegroup.assembler;

import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupDTO;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MachineGroup2DTOMapStruct {

    MachineGroup2DTOMapStruct INSTANCE = Mappers.getMapper(MachineGroup2DTOMapStruct.class);

    @Mappings({})
    MachineGroupDTO toDTO(MachineGroup machineGroup);

    @Mappings({})
    MachineGroup toDomain(MachineGroupDTO machineGroupDTO);

    List<MachineGroupDTO> toDTOList(List<MachineGroup> MachineGroupList);

    List<MachineGroup> toDomainList(List<MachineGroupDTO> MachineGroupDTOList);
}
