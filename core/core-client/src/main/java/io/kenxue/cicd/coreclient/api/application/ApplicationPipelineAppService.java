package io.kenxue.cicd.coreclient.api.application;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.*;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
public interface ApplicationPipelineAppService {
    Response add(ApplicationPipelineAddCmd cmd);
    Response update(ApplicationPipelineUpdateCmd cmd);
    Response delete(ApplicationPipelineDeleteCmd userDeleteCmd);
    SingleResponse<ApplicationPipelineDTO> getById(ApplicationPipelineGetQry qry);
    MultiResponse<ApplicationPipelineDTO> list(ApplicationPipelineListQry qry);
    PageResponse<ApplicationPipelineDTO> page(ApplicationPipelinePageQry userPageQry);
}
