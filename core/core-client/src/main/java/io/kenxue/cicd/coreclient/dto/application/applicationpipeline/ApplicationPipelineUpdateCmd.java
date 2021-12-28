package io.kenxue.cicd.coreclient.dto.application.applicationpipeline;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationPipelineUpdateCmd extends CommonCommand {

    @NotNull
    private ApplicationPipelineDTO applicationPipelineDTO;
}
