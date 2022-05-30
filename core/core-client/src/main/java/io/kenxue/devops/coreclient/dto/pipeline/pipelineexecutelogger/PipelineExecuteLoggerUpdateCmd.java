package io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Data
@Accessors(chain = true)
public class PipelineExecuteLoggerUpdateCmd extends CommonCommand {

    @NotNull
    private PipelineExecuteLoggerDTO pipelineExecuteLoggerDTO;
}
