package io.kenxue.devops.coreclient.api.application;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.*;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationPipelineAppService {
    Response add(PipelineAddCmd cmd);
    Response update(PipelineUpdateCmd cmd);
    Response delete(PipelineDeleteCmd userDeleteCmd);
    SingleResponse<PipelineDTO> getById(PipelineGetQry qry);
    MultiResponse<PipelineDTO> list(PipelineListQry qry);
    PageResponse<PipelineDTO> page(PipelinePageQry userPageQry);

    /**
     * 部署流水线
     * @param pipelineDeployCmd
     * @return
     */
    Response deploy(PipelineDeployCmd pipelineDeployCmd);

    /**
     * 执行流水线
     * @param pipelineExecuteCmd
     * @return
     */
    Response execute(PipelineExecuteCmd pipelineExecuteCmd);

    Response webhook(PipelineWebhookExecuteCmd setUuid);
}
