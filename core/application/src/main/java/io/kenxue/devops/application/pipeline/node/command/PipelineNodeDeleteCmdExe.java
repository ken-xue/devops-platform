package io.kenxue.devops.application.pipeline.node.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.application.PipelineNodeRepository;
import io.kenxue.devops.domain.domain.pipeline.PipelineNode;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Component
public class PipelineNodeDeleteCmdExe {
    @Resource
    private PipelineNodeRepository pipelineNodeRepository;

    public Response execute(PipelineNodeDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            PipelineNode pipelineNode =pipelineNodeRepository.getById(id);
            pipelineNode.deleted(UserThreadContext.get());
            pipelineNodeRepository.update(pipelineNode);
        }
        return Response.success();
    }
}
