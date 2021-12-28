package io.kenxue.cicd.coreclient.api.application;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.application.applicationconfig.*;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationConfigAppService {
    Response add(ApplicationConfigAddCmd cmd);
    Response update(ApplicationConfigUpdateCmd cmd);
    Response delete(ApplicationConfigDeleteCmd userDeleteCmd);
    SingleResponse<ApplicationConfigDTO> getById(ApplicationConfigGetQry qry);
    MultiResponse<ApplicationConfigDTO> list(ApplicationConfigListQry qry);
    PageResponse<ApplicationConfigDTO> page(ApplicationConfigPageQry userPageQry);
}
