package io.kenxue.devops.application.pipeline.nodeinfo.service;

import io.kenxue.devops.application.pipeline.nodeinfo.command.PipelineNodeInfoAddCmdExe;
import io.kenxue.devops.application.pipeline.nodeinfo.command.PipelineNodeInfoDeleteCmdExe;
import io.kenxue.devops.application.pipeline.nodeinfo.command.PipelineNodeInfoUpdateCmdExe;
import io.kenxue.devops.application.pipeline.nodeinfo.command.query.PipelineNodeInfoListQryExe;
import io.kenxue.devops.application.pipeline.nodeinfo.command.query.PipelineNodeInfoPageQryExe;
import io.kenxue.devops.coreclient.api.pipeline.PipelineNodeInfoAppService;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.*;
import io.kenxue.devops.application.pipeline.nodeinfo.command.query.PipelineNodeInfoGetQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

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
