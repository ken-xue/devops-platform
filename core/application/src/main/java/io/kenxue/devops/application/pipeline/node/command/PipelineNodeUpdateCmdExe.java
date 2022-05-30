package io.kenxue.devops.application.pipeline.node.command;

import io.kenxue.devops.application.pipeline.node.assembler.PipelineNode2DTOAssembler;
import io.kenxue.devops.domain.domain.pipeline.PipelineNode;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeUpdateCmd;
import io.kenxue.devops.domain.repository.application.PipelineNodeRepository;
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