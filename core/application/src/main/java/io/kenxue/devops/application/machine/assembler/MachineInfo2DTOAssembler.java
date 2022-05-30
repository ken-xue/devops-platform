package io.kenxue.devops.application.machine.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoDTO;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineInfo2DTOAssembler implements Assembler<MachineInfoDTO, MachineInfo> {

    @Override
    public MachineInfoDTO toDTO(MachineInfo machineInfo) {
        return MachineInfo2DTOMapStruct.INSTANCE.toDTO(machineInfo);
    }

    @Override
    public MachineInfo toDomain(MachineInfoDTO machineInfoDTO) {
        return MachineInfo2DTOMapStruct.INSTANCE.toDomain(machineInfoDTO);
    }

    @Override
    public List<MachineInfoDTO> toDTOList(List<MachineInfo> machineInfoList) {
        return MachineInfo2DTOMapStruct.INSTANCE.toDTOList(machineInfoList);
    }

    @Override
    public List<MachineInfo> toDomainList(List<MachineInfoDTO> machineInfoDTOList) {
        return MachineInfo2DTOMapStruct.INSTANCE.toDomainList(machineInfoDTOList);
    }

    @Override
    public Page<MachineInfoDTO> toDTOPage(Page<MachineInfo> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), MachineInfo2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
