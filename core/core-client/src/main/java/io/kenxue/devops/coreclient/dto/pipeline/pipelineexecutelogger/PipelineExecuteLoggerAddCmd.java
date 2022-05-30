package io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Data
@Accessors(chain = true)
public class PipelineExecuteLoggerAddCmd extends CommonCommand {
    @NotNull
    private PipelineExecuteLoggerDTO pipelineExecuteLoggerDTO;
}
