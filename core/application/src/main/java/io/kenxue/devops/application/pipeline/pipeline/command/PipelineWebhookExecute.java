package io.kenxue.devops.application.pipeline.pipeline.command;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineExecuteCmd;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineWebhookExecuteCmd;
import io.kenxue.devops.domain.domain.pipeline.Pipeline;
import io.kenxue.devops.domain.repository.pipeline.PipelineRepository;
import io.kenxue.devops.sharedataboject.pipeline.enums.PipelineTargetEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PipelineWebhookExecute {
    @Resource
    private PipelineRepository pipelineRepository;
    @Resource
    private PipelineExecuteCmdExe pipelineExecuteCmdExe;

    public Response execute(PipelineWebhookExecuteCmd cmd) {

        Pipeline pipeline = pipelineRepository.getByUUID(cmd.getUuid());
        PipelineTargetEnum webHook = PipelineTargetEnum.WEB_HOOK;
        return pipelineExecuteCmdExe.execute(
                new PipelineExecuteCmd()
                        .setId(pipeline.getId())
                        .setTargetUser(webHook.name())
                        .setTargetWay(webHook)
        );
    }
}
