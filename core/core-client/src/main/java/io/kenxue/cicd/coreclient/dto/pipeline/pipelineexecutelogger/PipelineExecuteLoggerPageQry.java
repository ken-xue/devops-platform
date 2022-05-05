package io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Data
@Accessors(chain = true)
public class PipelineExecuteLoggerPageQry extends PageQuery {

    private PipelineExecuteLoggerDTO pipelineExecuteLoggerDTO;

}
