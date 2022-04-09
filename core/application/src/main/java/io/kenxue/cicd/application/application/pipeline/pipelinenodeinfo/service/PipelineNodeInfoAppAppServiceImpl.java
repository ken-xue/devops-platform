package io.kenxue.cicd.application.application.pipeline.pipelinenodeinfo.service;

import io.kenxue.cicd.coreclient.api.pipeline.PipelineNodeInfoAppService;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.*;
import io.kenxue.cicd.application.application.pipeline.pipelinenodeinfo.command.PipelineNodeInfoAddCmdExe;
import io.kenxue.cicd.application.application.pipeline.pipelinenodeinfo.command.PipelineNodeInfoDeleteCmdExe;
import io.kenxue.cicd.application.application.pipeline.pipelinenodeinfo.command.PipelineNodeInfoUpdateCmdExe;
import io.kenxue.cicd.application.application.pipeline.pipelinenodeinfo.command.query.PipelineNodeInfoGetQryExe;
import io.kenxue.cicd.application.application.pipeline.pipelinenodeinfo.command.query.PipelineNodeInfoListQryExe;
import io.kenxue.cicd.application.application.pipeline.pipelinenodeinfo.command.query.PipelineNodeInfoPageQryExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Service
public class PipelineNodeInfoAppAppServiceImpl implements PipelineNodeInfoAppService {
    @Resource
    private PipelineNodeInfoAddCmdExe pipelineNodeInfoAddCmdExe;
    @Resource
    private PipelineNodeInfoUpdateCmdExe pipelineNodeInfoUpdateCmdExe;
    @Resource
    private PipelineNodeInfoGetQryExe pipelineNodeInfoGetQryExe;
    @Resource
    private PipelineNodeInfoListQryExe pipelineNodeInfoListQryExe;
    @Resource
    private PipelineNodeInfoPageQryExe pipelineNodeInfoPageQryExe;
    @Resource
    private PipelineNodeInfoDeleteCmdExe pipelineNodeInfoDeleteCmdExe;

    public Response add(PipelineNodeInfoAddCmd pipelineNodeInfoAddCmd) {
        return  pipelineNodeInfoAddCmdExe.execute(pipelineNodeInfoAddCmd);
    }

    public Response update(PipelineNodeInfoUpdateCmd cmd) {
        return pipelineNodeInfoUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<PipelineNodeInfoDTO> getById(PipelineNodeInfoGetQry qry) {
        return pipelineNodeInfoGetQryExe.execute(qry);
    }

    public MultiResponse<PipelineNodeInfoDTO> list(PipelineNodeInfoListQry qry) {
        return pipelineNodeInfoListQryExe.execute(qry);
    }

    public Response delete(PipelineNodeInfoDeleteCmd pipelineNodeInfoDeleteCmd) {
        return pipelineNodeInfoDeleteCmdExe.execute(pipelineNodeInfoDeleteCmd);
    }

    public PageResponse<PipelineNodeInfoDTO> page(PipelineNodeInfoPageQry pipelineNodeInfoPageQry) {
        return pipelineNodeInfoPageQryExe.execute(pipelineNodeInfoPageQry);
    }

}
