package io.kenxue.cicd.coreclient.dto.application.applicationpipeline;

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
public class ApplicationPipelineDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
