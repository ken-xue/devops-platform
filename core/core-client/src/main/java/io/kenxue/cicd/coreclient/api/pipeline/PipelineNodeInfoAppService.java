package io.kenxue.cicd.coreclient.api.pipeline;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.*;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
public interface PipelineNodeInfoAppService {
    Response add(PipelineNodeInfoAddCmd cmd);
    Response update(PipelineNodeInfoUpdateCmd cmd);
    Response delete(PipelineNodeInfoDeleteCmd userDeleteCmd);
    SingleResponse<PipelineNodeInfoDTO> getById(PipelineNodeInfoGetQry qry);
    MultiResponse<PipelineNodeInfoDTO> list(PipelineNodeInfoListQry qry);
    PageResponse<PipelineNodeInfoDTO> page(PipelineNodeInfoPageQry userPageQry);
}
