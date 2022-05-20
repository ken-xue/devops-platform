package io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.convertor;

import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.dataobject.MachineOfGroupDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroup2DOConvector implements Convector<MachineOfGroup,MachineOfGroupDO>{
    
    public MachineOfGroupDO toDO(MachineOfGroup machineOfGroup) {
        return MachineOfGroup2DOMapStruct.INSTANCE.toDO(machineOfGroup);
    }

    public MachineOfGroup toDomain(MachineOfGroupDO machineOfGroupDO) {
        return MachineOfGroup2DOMapStruct.INSTANCE.toDomain(machineOfGroupDO);
    }

    public List<MachineOfGroupDO> toDOList(List<MachineOfGroup> machineOfGroupList) {
        return MachineOfGroup2DOMapStruct.INSTANCE.toDOList(machineOfGroupList);
    }

    public List<MachineOfGroup> toDomainList(List<MachineOfGroupDO> machineOfGroupDOList) {
        return MachineOfGroup2DOMapStruct.INSTANCE.toDomainList(machineOfGroupDOList);
    }
}
