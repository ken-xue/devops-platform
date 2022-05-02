package io.kenxue.cicd.application.application.pipeline.pipeline.command;

import io.kenxue.cicd.application.application.pipeline.pipeline.assembler.ApplicationPipeline2DTOAssembler;
import io.kenxue.cicd.application.application.pipeline.pipeline.execute.ExecuteService;
import io.kenxue.cicd.application.application.pipeline.pipeline.manager.PipelineManager;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineAddCmd;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineExecuteCmd;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationPipelineExecuteCmdExe {

    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;
    @Resource
    private ApplicationPipeline2DTOAssembler applicationPipeline2DTOAssembler;
    @Resource
    private PipelineManager pipelineManager;
    @Resource
    private ExecuteService executeService;

    public Response execute(ApplicationPipelineExecuteCmd cmd) {

        ApplicationPipeline applicationPipeline = applicationPipelineRepository.getById(cmd.getId());

        ApplicationPipeline pipeline = pipelineManager.get(applicationPipeline.getUuid());

        //构建context

//        executeService.execute(pipeline);

        return Response.success();
    }
}
