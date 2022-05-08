package io.kenxue.cicd.application.pipeline.pipeline.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineDeleteCmd;
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
    private ApplicationPipelineRepository applicationPipelineRepository;

    public Response execute(PipelineDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Pipeline pipeline =applicationPipelineRepository.getById(id);
            pipeline.deleted(UserThreadContext.get());
            applicationPipelineRepository.update(pipeline);
        }
        return Response.success();
    }
}
