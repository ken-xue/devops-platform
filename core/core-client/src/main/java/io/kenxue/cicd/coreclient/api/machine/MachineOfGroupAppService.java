package io.kenxue.cicd.coreclient.api.machine;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.*;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
public interface MachineOfGroupAppService {
    Response add(MachineOfGroupAddCmd cmd);
    Response update(MachineOfGroupUpdateCmd cmd);
    Response delete(MachineOfGroupDeleteCmd userDeleteCmd);
    SingleResponse<MachineOfGroupDTO> getById(MachineOfGroupGetQry qry);
    MultiResponse<MachineOfGroupDTO> list(MachineOfGroupListQry qry);
    PageResponse<MachineOfGroupDTO> page(MachineOfGroupPageQry userPageQry);
}
