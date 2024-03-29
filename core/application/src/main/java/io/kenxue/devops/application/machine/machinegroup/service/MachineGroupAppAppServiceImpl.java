package io.kenxue.devops.application.machine.machinegroup.service;

import io.kenxue.devops.application.machine.machinegroup.command.MachineGroupAddCmdExe;
import io.kenxue.devops.application.machine.machinegroup.command.MachineGroupDeleteCmdExe;
import io.kenxue.devops.application.machine.machinegroup.command.MachineGroupUpdateCmdExe;
import io.kenxue.devops.application.machine.machinegroup.command.query.MachineGroupGetQryExe;
import io.kenxue.devops.application.machine.machinegroup.command.query.MachineGroupListQryExe;
import io.kenxue.devops.application.machine.machinegroup.command.query.MachineGroupPageQryExe;
import io.kenxue.devops.coreclient.api.machine.MachineGroupAppService;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.*;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Service
public class MachineGroupAppAppServiceImpl implements MachineGroupAppService {
    @Resource
    private MachineGroupAddCmdExe machineGroupAddCmdExe;
    @Resource
    private MachineGroupUpdateCmdExe machineGroupUpdateCmdExe;
    @Resource
    private MachineGroupGetQryExe machineGroupGetQryExe;
    @Resource
    private MachineGroupListQryExe machineGroupListQryExe;
    @Resource
    private MachineGroupPageQryExe machineGroupPageQryExe;
    @Resource
    private MachineGroupDeleteCmdExe machineGroupDeleteCmdExe;

    public Response add(MachineGroupAddCmd machineGroupAddCmd) {
        return  machineGroupAddCmdExe.execute(machineGroupAddCmd);
    }

    public Response update(MachineGroupUpdateCmd cmd) {
        return machineGroupUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<MachineGroupDTO> getById(MachineGroupGetQry qry) {
        return machineGroupGetQryExe.execute(qry);
    }

    public MultiResponse<MachineGroupDTO> list(MachineGroupListQry qry) {
        return machineGroupListQryExe.execute(qry);
    }

    public Response delete(MachineGroupDeleteCmd machineGroupDeleteCmd) {
        return machineGroupDeleteCmdExe.execute(machineGroupDeleteCmd);
    }

    public PageResponse<MachineGroupDTO> page(MachineGroupPageQry machineGroupPageQry) {
        return machineGroupPageQryExe.execute(machineGroupPageQry);
    }

}
