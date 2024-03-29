package io.kenxue.devops.application.machine.command.query;

import io.kenxue.devops.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoDTO;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoListQry;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.domain.repository.machine.MachineInfoRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineInfoListQryExe {
    @Resource
    private MachineInfoRepository machineInfoRepository;
    @Resource
    private MachineInfo2DTOAssembler machineInfo2DTOAssembler;

    public MultiResponse<MachineInfoDTO> execute(MachineInfoListQry qry) {
        List<MachineInfo> machineInfoDTOList = machineInfoRepository.list(qry);
        return MultiResponse.of(machineInfo2DTOAssembler.toDTOList(machineInfoDTOList));
    }
}
