package io.kenxue.cicd.application.pipeline.node.service;

import io.kenxue.cicd.application.pipeline.node.command.PipelineNodeAddCmdExe;
import io.kenxue.cicd.application.pipeline.node.command.PipelineNodeDeleteCmdExe;
import io.kenxue.cicd.application.pipeline.node.command.PipelineNodeUpdateCmdExe;
import io.kenxue.cicd.application.pipeline.node.command.query.PipelineNodeGetQryExe;
import io.kenxue.cicd.application.pipeline.node.command.query.PipelineNodeListQryExe;
import io.kenxue.cicd.application.pipeline.node.command.query.PipelineNodePageQryExe;
import io.kenxue.cicd.coreclient.api.application.PipelineNodeAppService;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenode.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Service
public class PipelineNodeAppAppServiceImpl implements PipelineNodeAppService {
    @Resource
    private PipelineNodeAddCmdExe pipelineNodeAddCmdExe;
    @Resource
    private PipelineNodeUpdateCmdExe pipelineNodeUpdateCmdExe;
    @Resource
    private PipelineNodeGetQryExe pipelineNodeGetQryExe;
    @Resource
    private PipelineNodeListQryExe pipelineNodeListQryExe;
    @Resource
    private PipelineNodePageQryExe pipelineNodePageQryExe;
    @Resource
    private PipelineNodeDeleteCmdExe pipelineNodeDeleteCmdExe;

    public Response add(PipelineNodeAddCmd pipelineNodeAddCmd) {
        return  pipelineNodeAddCmdExe.execute(pipelineNodeAddCmd);
    }

    public Response update(PipelineNodeUpdateCmd cmd) {
        return pipelineNodeUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<PipelineNodeDTO> getById(PipelineNodeGetQry qry) {
        return pipelineNodeGetQryExe.execute(qry);
    }

    public MultiResponse<PipelineNodeDTO> list(PipelineNodeListQry qry) {
        return pipelineNodeListQryExe.execute(qry);
    }

    public Response delete(PipelineNodeDeleteCmd pipelineNodeDeleteCmd) {
        return pipelineNodeDeleteCmdExe.execute(pipelineNodeDeleteCmd);
    }

    public PageResponse<PipelineNodeDTO> page(PipelineNodePageQry pipelineNodePageQry) {
        return pipelineNodePageQryExe.execute(pipelineNodePageQry);
    }

}
