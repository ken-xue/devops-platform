package io.kenxue.devops.application.machine.machineofgroup.service;

import io.kenxue.devops.application.machine.machineofgroup.command.MachineOfGroupAddCmdExe;
import io.kenxue.devops.application.machine.machineofgroup.command.MachineOfGroupDeleteCmdExe;
import io.kenxue.devops.application.machine.machineofgroup.command.MachineOfGroupUpdateCmdExe;
import io.kenxue.devops.application.machine.machineofgroup.command.query.MachineOfGroupGetQryExe;
import io.kenxue.devops.application.machine.machineofgroup.command.query.MachineOfGroupListQryExe;
import io.kenxue.devops.application.machine.machineofgroup.command.query.MachineOfGroupPageQryExe;
import io.kenxue.devops.coreclient.api.machine.MachineOfGroupAppService;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Service
public class MachineOfGroupAppAppServiceImpl implements MachineOfGroupAppService {
    @Resource
    private MachineOfGroupAddCmdExe machineOfGroupAddCmdExe;
    @Resource
    private MachineOfGroupUpdateCmdExe machineOfGroupUpdateCmdExe;
    @Resource
    private MachineOfGroupGetQryExe machineOfGroupGetQryExe;
    @Resource
    private MachineOfGroupListQryExe machineOfGroupListQryExe;
    @Resource
    private MachineOfGroupPageQryExe machineOfGroupPageQryExe;
    @Resource
    private MachineOfGroupDeleteCmdExe machineOfGroupDeleteCmdExe;

    public Response add(MachineOfGroupAddCmd machineOfGroupAddCmd) {
        return  machineOfGroupAddCmdExe.execute(machineOfGroupAddCmd);
    }

    public Response update(MachineOfGroupUpdateCmd cmd) {
        return machineOfGroupUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<MachineOfGroupDTO> getById(MachineOfGroupGetQry qry) {
        return machineOfGroupGetQryExe.execute(qry);
    }

    public MultiResponse<MachineOfGroupDTO> list(MachineOfGroupListQry qry) {
        return machineOfGroupListQryExe.execute(qry);
    }

    public Response delete(MachineOfGroupDeleteCmd machineOfGroupDeleteCmd) {
        return machineOfGroupDeleteCmdExe.execute(machineOfGroupDeleteCmd);
    }

    public PageResponse<MachineOfGroupDTO> page(MachineOfGroupPageQry machineOfGroupPageQry) {
        return machineOfGroupPageQryExe.execute(machineOfGroupPageQry);
    }

}
