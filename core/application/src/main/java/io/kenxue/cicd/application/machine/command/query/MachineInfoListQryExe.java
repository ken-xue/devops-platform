package io.kenxue.cicd.application.machine.command.query;

import io.kenxue.cicd.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoDTO;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoListQry;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
