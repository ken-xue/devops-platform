package io.kenxue.cicd.coreclient.api.machine;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.*;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
public interface MachineGroupAppService {
    Response add(MachineGroupAddCmd cmd);
    Response update(MachineGroupUpdateCmd cmd);
    Response delete(MachineGroupDeleteCmd userDeleteCmd);
    SingleResponse<MachineGroupDTO> getById(MachineGroupGetQry qry);
    MultiResponse<MachineGroupDTO> list(MachineGroupListQry qry);
    PageResponse<MachineGroupDTO> page(MachineGroupPageQry userPageQry);
}
