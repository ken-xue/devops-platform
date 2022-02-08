package io.kenxue.cicd.application.application.machine.command.query;

import io.kenxue.cicd.application.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoGetQry;
import io.kenxue.cicd.domain.repository.application.MachineInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineInfoGetQryExe {

    @Resource
    private MachineInfoRepository machineInfoRepository;
    @Resource
    private MachineInfo2DTOAssembler machineInfo2DTOAssembler;

    public SingleResponse<MachineInfoDTO> execute(MachineInfoGetQry qry) {
        return SingleResponse.of(machineInfo2DTOAssembler.toDTO(machineInfoRepository.getById(qry.getId())));
    }

}
