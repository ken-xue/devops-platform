package io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@Accessors(chain = true)
public class PipelineNodeInfoUpdateCmd extends CommonCommand {

    @NotNull
    private PipelineNodeInfoDTO pipelineNodeInfoDTO;
}
