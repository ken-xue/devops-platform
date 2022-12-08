package io.kenxue.devops.application.pipeline.node.command;

import io.kenxue.devops.application.pipeline.node.assembler.PipelineNode2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.application.PipelineNodeRepository;
import io.kenxue.devops.domain.domain.pipeline.PipelineNode;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeAddCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Component
public class PipelineNodeAddCmdExe {

    @Resource
    private PipelineNodeRepository pipelineNodeRepository;
    @Resource
    private PipelineNode2DTOAssembler pipelineNode2DTOAssembler;

    public Response execute(PipelineNodeAddCmd cmd) {
        PipelineNode pipelineNode = pipelineNode2DTOAssembler.toDomain(cmd.getPipelineNodeDTO());
        pipelineNode.create(UserThreadContext.get());
        pipelineNodeRepository.create(pipelineNode);
        return Response.success();
    }
}
