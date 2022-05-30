package io.kenxue.devops.application.pipeline.pipeline.command;

import io.kenxue.devops.domain.domain.pipeline.Pipeline;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineDeployCmd;
import io.kenxue.devops.domain.repository.pipeline.PipelineRepository;
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
