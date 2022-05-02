package io.kenxue.cicd.coreclient.dto.pipeline.pipeline;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationPipelineDeployCmd extends CommonCommand {
    @NotNull(message = "流水线Id不能为空")
    private Long id;
}
