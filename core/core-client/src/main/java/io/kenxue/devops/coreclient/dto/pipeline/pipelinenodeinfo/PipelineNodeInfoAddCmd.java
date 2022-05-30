package io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@Accessors(chain = true)
public class PipelineNodeInfoAddCmd extends CommonCommand {
    @NotNull
    private PipelineNodeInfoDTO pipelineNodeInfoDTO;
}
