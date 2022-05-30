package io.kenxue.devops.coreclient.api.application;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.*;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
public interface PipelineNodeAppService {
    Response add(PipelineNodeAddCmd cmd);
    Response update(PipelineNodeUpdateCmd cmd);
    Response delete(PipelineNodeDeleteCmd userDeleteCmd);
    SingleResponse<PipelineNodeDTO> getById(PipelineNodeGetQry qry);
    MultiResponse<PipelineNodeDTO> list(PipelineNodeListQry qry);
    PageResponse<PipelineNodeDTO> page(PipelineNodePageQry userPageQry);
}
