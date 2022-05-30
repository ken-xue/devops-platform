package io.kenxue.devops.coreclient.dto.pipeline.pipelinenode;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Data
@Accessors(chain = true)
public class PipelineNodeAddCmd extends CommonCommand {
    @NotNull
    private PipelineNodeDTO pipelineNodeDTO;
}
