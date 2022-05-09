package io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.convertor;

import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.dataobject.MachineGroupDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Convector;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroup2DOConvector implements Convector<MachineGroup,MachineGroupDO>{
    
    public MachineGroupDO toDO(MachineGroup machineGroup) {
        return MachineGroup2DOMapStruct.INSTANCE.toDO(machineGroup);
    }

    public MachineGroup toDomain(MachineGroupDO machineGroupDO) {
        return MachineGroup2DOMapStruct.INSTANCE.toDomain(machineGroupDO);
    }

    public List<MachineGroupDO> toDOList(List<MachineGroup> machineGroupList) {
        return MachineGroup2DOMapStruct.INSTANCE.toDOList(machineGroupList);
    }

    public List<MachineGroup> toDomainList(List<MachineGroupDO> machineGroupDOList) {
        return MachineGroup2DOMapStruct.INSTANCE.toDomainList(machineGroupDOList);
    }
}
