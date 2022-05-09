package io.kenxue.cicd.application.pipeline.pipeline.command;

import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineDeployCmd;
import io.kenxue.cicd.domain.repository.pipeline.PipelineRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 部署流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class PipelineDeployCmdExe {
    @Resource
    private PipelineRepository pipelineRepository;

    public Response execute(PipelineDeployCmd cmd) {

        Pipeline pipeline = pipelineRepository.getById(cmd.getId());

        ExecuteContext context = new ExecuteContext();

        return Response.success();
    }
}
