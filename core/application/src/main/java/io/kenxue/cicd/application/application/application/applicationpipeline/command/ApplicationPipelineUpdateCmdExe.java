package io.kenxue.cicd.application.application.application.applicationpipeline.command;

import io.kenxue.cicd.application.application.application.applicationpipeline.assembler.ApplicationPipeline2DTOAssembler;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineUpdateCmd;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Component
public class ApplicationPipelineUpdateCmdExe {

    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;
    @Resource
    private ApplicationPipeline2DTOAssembler applicationPipeline2DTOAssembler;

    public Response execute(ApplicationPipelineUpdateCmd cmd) {
        ApplicationPipeline applicationPipeline = applicationPipeline2DTOAssembler.toDomain(cmd.getApplicationPipelineDTO());
        applicationPipelineRepository.update(applicationPipeline);
        return Response.success();
    }
}