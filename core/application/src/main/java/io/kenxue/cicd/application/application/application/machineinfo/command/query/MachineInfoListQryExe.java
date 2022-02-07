package io.kenxue.cicd.application.application.application.machineinfo.command.query;

import io.kenxue.cicd.application.application.application.machineinfo.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoDTO;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoListQry;
import io.kenxue.cicd.domain.domain.application.MachineInfo;
import io.kenxue.cicd.domain.repository.application.MachineInfoRepository;
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
