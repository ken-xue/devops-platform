package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.MachineInfo;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.MachineInfoDO;
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
public interface MachineInfo2DOMapStruct {

    MachineInfo2DOMapStruct INSTANCE = Mappers.getMapper(MachineInfo2DOMapStruct.class);

    @Mappings({})
    MachineInfoDO toDO(MachineInfo machineInfo);

    @Mappings({})
    MachineInfo toDomain(MachineInfoDO machineInfoDO);

    List<MachineInfoDO> toDOList(List<MachineInfo> machineInfoList);

    List<MachineInfo> toDomainList(List<MachineInfoDO> machineInfoDOList);
}
