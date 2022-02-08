package io.kenxue.cicd.application.application.machine.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoDTO;
import io.kenxue.cicd.domain.domain.application.MachineInfo;
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
