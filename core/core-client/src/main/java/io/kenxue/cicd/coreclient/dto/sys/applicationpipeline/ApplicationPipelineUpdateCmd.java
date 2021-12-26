package io.kenxue.cicd.coreclient.dto.sys.applicationpipeline;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationPipelineUpdateCmd extends CommonCommand {

    @NotNull
    private ApplicationPipelineDTO applicationPipelineDTO;
}
