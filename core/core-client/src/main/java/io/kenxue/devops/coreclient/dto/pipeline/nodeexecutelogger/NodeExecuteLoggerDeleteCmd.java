package io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;

/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Data
@Accessors(chain = true)
public class NodeExecuteLoggerDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
