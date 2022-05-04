package io.kenxue.cicd.application.pipeline.pipelinenode.command;

import io.kenxue.cicd.application.pipeline.pipelinenode.assembler.PipelineNode2DTOAssembler;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNode;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenode.PipelineNodeUpdateCmd;
import io.kenxue.cicd.domain.repository.application.PipelineNodeRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Component
public class PipelineNodeUpdateCmdExe {

    @Resource
    private PipelineNodeRepository pipelineNodeRepository;
    @Resource
    private PipelineNode2DTOAssembler pipelineNode2DTOAssembler;

    public Response execute(PipelineNodeUpdateCmd cmd) {
        PipelineNode pipelineNode = pipelineNode2DTOAssembler.toDomain(cmd.getPipelineNodeDTO());
        pipelineNodeRepository.update(pipelineNode);
        return Response.success();
    }
}