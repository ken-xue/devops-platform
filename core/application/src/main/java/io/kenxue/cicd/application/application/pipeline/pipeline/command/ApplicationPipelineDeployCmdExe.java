package io.kenxue.cicd.application.application.pipeline.pipeline.command;

import io.kenxue.cicd.application.application.pipeline.pipeline.manager.PipelineManager;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineDeployCmd;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 部署流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationPipelineDeployCmdExe {

    @Resource
    private PipelineManager pipelineManager;
    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;

    public Response execute(ApplicationPipelineDeployCmd cmd) {

        ApplicationPipeline pipeline = applicationPipelineRepository.getById(cmd.getId());
        //编译
        pipeline.compile();
        //部署
        pipeline.deploy();
        //加入缓存
        pipelineManager.add(pipeline);

        return Response.success();
    }
}
