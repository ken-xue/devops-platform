package io.kenxue.cicd.application.application.application.pipelinenode.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.application.PipelineNodeRepository;
import io.kenxue.cicd.domain.domain.application.PipelineNode;
import io.kenxue.cicd.coreclient.dto.application.pipelinenode.PipelineNodeDeleteCmd;
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
