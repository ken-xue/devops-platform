package io.kenxue.cicd.application.application.application.applicationpipeline.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationPipelineDeleteCmdExe {
    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;

    public Response execute(ApplicationPipelineDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            ApplicationPipeline applicationPipeline =applicationPipelineRepository.getById(id);
            applicationPipeline.deleted(UserThreadContext.get());
            applicationPipelineRepository.update(applicationPipeline);
        }
        return Response.success();
    }
}
