package io.kenxue.cicd.coreclient.api.application;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.machine.*;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
public interface MachineInfoAppService {
    Response add(MachineInfoAddCmd cmd);
    Response update(MachineInfoUpdateCmd cmd);
    Response delete(MachineInfoDeleteCmd userDeleteCmd);
    SingleResponse<MachineInfoDTO> getById(MachineInfoGetQry qry);
    MultiResponse<MachineInfoDTO> list(MachineInfoListQry qry);
    PageResponse<MachineInfoDTO> page(MachineInfoPageQry userPageQry);

    /**
     * 测试连接
     * @param machineInfoAddCmd
     * @return
     */
    Response testConn(MachineInfoAddCmd machineInfoAddCmd);

    /**
     * 添加秘钥
     * @param machineInfoAddCmd
     * @return
     */
    Response addSecretKey(MachineInfoAddCmd machineInfoAddCmd);
}
