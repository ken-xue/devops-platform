package io.kenxue.cicd.application.pipeline.pipeline.command;

import io.kenxue.cicd.application.pipeline.pipeline.assembler.ApplicationPipeline2DTOAssembler;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineUpdateCmd;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationPipelineUpdateCmdExe {

    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;
    @Resource
    private ApplicationPipeline2DTOAssembler applicationPipeline2DTOAssembler;

    public Response execute(ApplicationPipelineUpdateCmd cmd) {
        Pipeline pipeline = applicationPipeline2DTOAssembler.toDomain(cmd.getApplicationPipelineDTO());
        applicationPipelineRepository.update(pipeline);
        return Response.success();
    }
}