package io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Data
@Accessors(chain = true)
public class NodeExecuteLoggerPageQry extends PageQuery {

    private NodeExecuteLoggerDTO nodeExecuteLoggerDTO;

}
