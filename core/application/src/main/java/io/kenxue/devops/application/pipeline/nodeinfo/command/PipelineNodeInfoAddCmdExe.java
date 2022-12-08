package io.kenxue.devops.application.pipeline.nodeinfo.command;

import io.kenxue.devops.application.pipeline.nodeinfo.assembler.PipelineNodeInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.pipeline.PipelineNodeInfoRepository;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoAddCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
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
