package io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
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
public class NodeExecuteLoggerDTO extends CommonDTO {
    /**
     * 流水线执行记录uuid
     */
    private String pipelineExecuteLoggerUuid;
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
    /**
     * 最终执行状态
     */
    private String finalStatus;
}
