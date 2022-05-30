package io.kenxue.devops.coreclient.dto.pipeline.pipeline;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PipelineWebhookExecuteCmd extends CommonCommand {
    private String uuid;
}
