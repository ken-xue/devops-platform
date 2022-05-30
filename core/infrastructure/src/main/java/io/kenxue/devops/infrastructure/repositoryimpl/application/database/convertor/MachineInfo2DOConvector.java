package io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.MachineInfoDO;
import org.springframework.stereotype.Component;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineInfo2DOConvector implements Convector<MachineInfo,MachineInfoDO>{
    
    public MachineInfoDO toDO(MachineInfo machineInfo) {
        return MachineInfo2DOMapStruct.INSTANCE.toDO(machineInfo);
    }

    public MachineInfo toDomain(MachineInfoDO machineInfoDO) {
        return MachineInfo2DOMapStruct.INSTANCE.toDomain(machineInfoDO);
    }

    public List<MachineInfoDO> toDOList(List<MachineInfo> machineInfoList) {
        return MachineInfo2DOMapStruct.INSTANCE.toDOList(machineInfoList);
    }

    public List<MachineInfo> toDomainList(List<MachineInfoDO> machineInfoDOList) {
        return MachineInfo2DOMapStruct.INSTANCE.toDomainList(machineInfoDOList);
    }
}
