package io.kenxue.devops.application.machine.assembler;

import io.kenxue.devops.coreclient.dto.machine.MachineInfoDTO;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MachineInfo2DTOMapStruct {

    MachineInfo2DTOMapStruct INSTANCE = Mappers.getMapper(MachineInfo2DTOMapStruct.class);

    @Mappings({})
    MachineInfoDTO toDTO(MachineInfo machineInfo);

    @Mappings({})
    MachineInfo toDomain(MachineInfoDTO machineInfoDTO);

    List<MachineInfoDTO> toDTOList(List<MachineInfo> MachineInfoList);

    List<MachineInfo> toDomainList(List<MachineInfoDTO> MachineInfoDTOList);
}
