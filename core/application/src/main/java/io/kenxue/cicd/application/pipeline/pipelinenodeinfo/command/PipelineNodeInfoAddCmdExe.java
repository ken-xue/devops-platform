package io.kenxue.cicd.application.pipeline.pipelinenodeinfo.command;

import io.kenxue.cicd.application.pipeline.pipelinenodeinfo.assembler.PipelineNodeInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.pipeline.PipelineNodeInfoRepository;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Component
public class PipelineNodeInfoAddCmdExe {

    @Resource
    private PipelineNodeInfoRepository pipelineNodeInfoRepository;
    @Resource
    private PipelineNodeInfo2DTOAssembler pipelineNodeInfo2DTOAssembler;

    public Response execute(PipelineNodeInfoAddCmd cmd) {
        PipelineNodeInfo pipelineNodeInfo = pipelineNodeInfo2DTOAssembler.toDomain(cmd.getPipelineNodeInfoDTO());
        pipelineNodeInfo.create(UserThreadContext.get());
        pipelineNodeInfoRepository.create(pipelineNodeInfo);
        return Response.success();
    }
}
