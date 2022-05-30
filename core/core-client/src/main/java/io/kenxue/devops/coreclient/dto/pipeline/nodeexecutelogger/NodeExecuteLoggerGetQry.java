package io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Data
@Accessors(chain = true)
public class NodeExecuteLoggerGetQry extends CommonCommand {

    private Long id;
    /**
     * 流水线执行记录uuid
     */
    private String loggerUuid;
    /**
     * 节点uuid
     */
    private String nodeUuid;
    /**
     * 存储数据正文
     */
    private String logger;
    /**
     * 执行时间
     */
    private Date executeStartTime;
    /**
     * 结束时间
     */
    private Date executeEndTime;
}