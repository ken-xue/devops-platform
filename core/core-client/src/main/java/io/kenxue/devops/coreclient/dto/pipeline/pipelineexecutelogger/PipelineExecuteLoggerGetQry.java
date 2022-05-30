package io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Data
@Accessors(chain = true)
public class PipelineExecuteLoggerGetQry extends CommonCommand {

    private Long id;
    /**
     * 流水线uuid
     */
    private String pipelineUuid;
    /**
     * 存储数据正文
     */
    private String executeContext;
    /**
     * 执行时间
     */
    private Date executeStartTime;
    /**
     * 结束时间
     */
    private Date executeEndTime;
}