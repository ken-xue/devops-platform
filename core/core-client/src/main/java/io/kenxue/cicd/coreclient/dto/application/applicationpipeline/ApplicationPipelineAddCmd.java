package io.kenxue.cicd.coreclient.dto.application.applicationpipeline;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@Accessors(chain = true)
public class ApplicationPipelineAddCmd extends CommonCommand {
    @NotNull
    private ApplicationPipelineDTO applicationPipelineDTO;
}
