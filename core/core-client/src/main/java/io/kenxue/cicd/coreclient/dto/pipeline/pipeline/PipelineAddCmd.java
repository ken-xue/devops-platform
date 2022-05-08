package io.kenxue.cicd.coreclient.dto.pipeline.pipeline;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class PipelineAddCmd extends CommonCommand {
    @NotNull
    private PipelineDTO pipelineDTO;
}
