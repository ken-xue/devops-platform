package io.kenxue.cicd.application.pipeline.pipelinenodeinfo.command;

import io.kenxue.cicd.application.pipeline.pipelinenodeinfo.assembler.PipelineNodeInfo2DTOAssembler;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoUpdateCmd;
import io.kenxue.cicd.domain.repository.pipeline.PipelineNodeInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Component
public class PipelineNodeInfoUpdateCmdExe {

    @Resource
    private PipelineNodeInfoRepository pipelineNodeInfoRepository;
    @Resource
    private PipelineNodeInfo2DTOAssembler pipelineNodeInfo2DTOAssembler;

    public Response execute(PipelineNodeInfoUpdateCmd cmd) {
        PipelineNodeInfo pipelineNodeInfo = pipelineNodeInfo2DTOAssembler.toDomain(cmd.getPipelineNodeInfoDTO());
        pipelineNodeInfoRepository.update(pipelineNodeInfo);
        return Response.success();
    }
}