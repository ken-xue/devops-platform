package io.kenxue.cicd.application.application.application.pipelinenode.command;

import io.kenxue.cicd.application.application.application.pipelinenode.assembler.PipelineNode2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.application.PipelineNodeRepository;
import io.kenxue.cicd.domain.domain.application.PipelineNode;
import io.kenxue.cicd.coreclient.dto.application.pipelinenode.PipelineNodeAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
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