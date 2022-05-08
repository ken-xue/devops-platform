package io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
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
public class PipelineExecuteLoggerDTO extends CommonDTO {
    /**
     * 流水线uuid
     */
    private String pipelineUuid;
    /**
     * 存储数据正文
     */
    private String content;
    /**
     * 流水线快照
     */
    private String graphContent;
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
