package io.kenxue.devops.application.pipeline.pipeline.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.pipeline.PipelineRepository;
import io.kenxue.devops.domain.domain.pipeline.Pipeline;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class PipelineDeleteCmdExe {
    @Resource
    private PipelineRepository pipelineRepository;

    public Response execute(PipelineDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Pipeline pipeline = pipelineRepository.getById(id);
            pipeline.deleted(UserThreadContext.get());
            pipelineRepository.update(pipeline);
        }
        return Response.success();
    }
}
