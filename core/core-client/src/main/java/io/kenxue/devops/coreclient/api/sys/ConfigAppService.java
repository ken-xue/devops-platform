package io.kenxue.devops.coreclient.api.sys;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.sys.config.*;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
public interface ConfigAppService {
    Response add(ConfigAddCmd cmd);
    Response update(ConfigUpdateCmd cmd);
    Response delete(ConfigDeleteCmd userDeleteCmd);
    SingleResponse<ConfigDTO> getById(ConfigGetQry qry);
    MultiResponse<ConfigDTO> list(ConfigListQry qry);
    PageResponse<ConfigDTO> page(ConfigPageQry userPageQry);
}
