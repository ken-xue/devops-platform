package io.kenxue.cicd.application.application.machine.assembler;

import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoDTO;
import io.kenxue.cicd.domain.domain.application.MachineInfo;
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
