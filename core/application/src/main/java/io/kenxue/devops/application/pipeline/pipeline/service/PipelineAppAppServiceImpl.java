package io.kenxue.devops.application.pipeline.pipeline.service;

import io.kenxue.devops.application.pipeline.pipeline.command.*;
import io.kenxue.devops.application.pipeline.pipeline.command.query.PipelineGetQryExe;
import io.kenxue.devops.application.pipeline.pipeline.command.query.PipelineListQryExe;
import io.kenxue.devops.application.pipeline.pipeline.command.query.PipelinePageQryExe;
import io.kenxue.devops.coreclient.api.application.ApplicationPipelineAppService;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Service
public class PipelineAppAppServiceImpl implements ApplicationPipelineAppService {
    @Resource
    private PipelineAddCmdExe pipelineAddCmdExe;
    @Resource
    private PipelineExecuteCmdExe pipelineExecuteCmdExe;
    @Resource
    private PipelineDeployCmdExe pipelineDeployCmdExe;
    @Resource
    private PipelineUpdateCmdExe pipelineUpdateCmdExe;
    @Resource
    private PipelineGetQryExe pipelineGetQryExe;
    @Resource
    private PipelineListQryExe pipelineListQryExe;
    @Resource
    private PipelinePageQryExe pipelinePageQryExe;
    @Resource
    private PipelineDeleteCmdExe pipelineDeleteCmdExe;

    public Response add(PipelineAddCmd pipelineAddCmd) {
        return  pipelineAddCmdExe.execute(pipelineAddCmd);
    }

    public Response update(PipelineUpdateCmd cmd) {
        return pipelineUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<PipelineDTO> getById(PipelineGetQry qry) {
        return pipelineGetQryExe.execute(qry);
    }

    public MultiResponse<PipelineDTO> list(PipelineListQry qry) {
        return pipelineListQryExe.execute(qry);
    }

    public Response delete(PipelineDeleteCmd pipelineDeleteCmd) {
        return pipelineDeleteCmdExe.execute(pipelineDeleteCmd);
    }

    public PageResponse<PipelineDTO> page(PipelinePageQry pipelinePageQry) {
        return pipelinePageQryExe.execute(pipelinePageQry);
    }

    @Override
    public Response deploy(PipelineDeployCmd pipelineDeployCmd) {
        return pipelineDeployCmdExe.execute(pipelineDeployCmd);
    }

    @Override
    public Response execute(PipelineExecuteCmd pipelineExecuteCmd) {
        return pipelineExecuteCmdExe.execute(pipelineExecuteCmd);
    }

    @Override
    public Response webhook(PipelineWebhookExecuteCmd setUuid) {
        return null;
    }

}
