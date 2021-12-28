package io.kenxue.cicd.coreclient.api.application;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.*;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationInfoAppService {
    Response add(ApplicationInfoAddCmd cmd);
    Response update(ApplicationInfoUpdateCmd cmd);
    Response delete(ApplicationInfoDeleteCmd userDeleteCmd);
    SingleResponse<ApplicationInfoDTO> getById(ApplicationInfoGetQry qry);
    MultiResponse<ApplicationInfoDTO> list(ApplicationInfoListQry qry);
    PageResponse<ApplicationInfoDTO> page(ApplicationInfoPageQry userPageQry);
}
