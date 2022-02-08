package io.kenxue.cicd.application.application.machine.service;

import io.kenxue.cicd.application.application.machine.command.MachineTestConnCmdExe;
import io.kenxue.cicd.application.application.machine.command.MachineInfoAddCmdExe;
import io.kenxue.cicd.application.application.machine.command.MachineInfoUpdateCmdExe;
import io.kenxue.cicd.application.application.machine.command.query.MachineInfoGetQryExe;
import io.kenxue.cicd.application.application.machine.command.query.MachineInfoListQryExe;
import io.kenxue.cicd.application.application.machine.command.query.MachineInfoPageQryExe;
import io.kenxue.cicd.coreclient.api.application.MachineInfoAppService;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.*;
import io.kenxue.cicd.application.application.machine.command.MachineInfoDeleteCmdExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
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

}
