package io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.convertor;

import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.dataobject.MachineGroupDO;
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
public interface MachineGroup2DOMapStruct {

    MachineGroup2DOMapStruct INSTANCE = Mappers.getMapper(MachineGroup2DOMapStruct.class);

    @Mappings({})
    MachineGroupDO toDO(MachineGroup machineGroup);

    @Mappings({})
    MachineGroup toDomain(MachineGroupDO machineGroupDO);

    List<MachineGroupDO> toDOList(List<MachineGroup> machineGroupList);

    List<MachineGroup> toDomainList(List<MachineGroupDO> machineGroupDOList);
}
