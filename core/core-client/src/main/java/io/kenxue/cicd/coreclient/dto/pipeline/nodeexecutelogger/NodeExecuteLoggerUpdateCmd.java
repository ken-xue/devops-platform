package io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Data
@Accessors(chain = true)
public class NodeExecuteLoggerUpdateCmd extends CommonCommand {

    @NotNull
    private NodeExecuteLoggerDTO nodeExecuteLoggerDTO;
}
