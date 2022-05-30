package io.kenxue.devops.application.machine.service;

import io.kenxue.devops.application.machine.command.*;
import io.kenxue.devops.application.machine.command.query.MachineInfoGetQryExe;
import io.kenxue.devops.application.machine.command.query.MachineInfoListQryExe;
import io.kenxue.devops.application.machine.command.query.MachineInfoPageQryExe;
import io.kenxue.devops.coreclient.api.application.MachineInfoAppService;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.machine.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Service
public class MachineInfoAppAppServiceImpl implements MachineInfoAppService {
    @Resource
    private MachineInfoAddCmdExe machineInfoAddCmdExe;
    @Resource
    private MachineTestConnCmdExe machineTestConnCmdExe;
    @Resource
    private MachineAddSecretCmdExe machineAddSecretCmdExe;
    @Resource
    private MachineInfoUpdateCmdExe machineInfoUpdateCmdExe;
    @Resource
    private MachineInfoGetQryExe machineInfoGetQryExe;
    @Resource
    private MachineInfoListQryExe machineInfoListQryExe;
    @Resource
    private MachineInfoPageQryExe machineInfoPageQryExe;
    @Resource
    private MachineInfoDeleteCmdExe machineInfoDeleteCmdExe;

    public Response add(MachineInfoAddCmd machineInfoAddCmd) {
        return  machineInfoAddCmdExe.execute(machineInfoAddCmd);
    }

    public Response update(MachineInfoUpdateCmd cmd) {
        return machineInfoUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<MachineInfoDTO> getById(MachineInfoGetQry qry) {
        return machineInfoGetQryExe.execute(qry);
    }

    public MultiResponse<MachineInfoDTO> list(MachineInfoListQry qry) {
        return machineInfoListQryExe.execute(qry);
    }

    public Response delete(MachineInfoDeleteCmd machineInfoDeleteCmd) {
        return machineInfoDeleteCmdExe.execute(machineInfoDeleteCmd);
    }

    public PageResponse<MachineInfoDTO> page(MachineInfoPageQry machineInfoPageQry) {
        return machineInfoPageQryExe.execute(machineInfoPageQry);
    }

    @Override
    public Response testConn(MachineInfoAddCmd machineInfoAddCmd) {
        return machineTestConnCmdExe.execute(machineInfoAddCmd);
    }

    @Override
    public Response addSecretKey(MachineInfoAddCmd machineInfoAddCmd) {
        return machineAddSecretCmdExe.execute(machineInfoAddCmd);
    }

}
