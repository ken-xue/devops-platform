package io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.convertor;

import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.dataobject.MachineOfGroupDO;
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
public interface MachineOfGroup2DOMapStruct {

    MachineOfGroup2DOMapStruct INSTANCE = Mappers.getMapper(MachineOfGroup2DOMapStruct.class);

    @Mappings({})
    MachineOfGroupDO toDO(MachineOfGroup machineOfGroup);

    @Mappings({})
    MachineOfGroup toDomain(MachineOfGroupDO machineOfGroupDO);

    List<MachineOfGroupDO> toDOList(List<MachineOfGroup> machineOfGroupList);

    List<MachineOfGroup> toDomainList(List<MachineOfGroupDO> machineOfGroupDOList);
}
