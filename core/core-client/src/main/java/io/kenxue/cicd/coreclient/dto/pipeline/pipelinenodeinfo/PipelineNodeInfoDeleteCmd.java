package io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@Accessors(chain = true)
public class PipelineNodeInfoDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
