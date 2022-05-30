package io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Data
@TableName("pipeline_logger")
public class PipelineExecuteLoggerDO extends CommonEntity {
    /**
     * 流水线uuid
     */
    private String pipelineUuid;
    /**
     * 存储数据正文
     */
    private String content;
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
    /**
     * 流水线快照
     */
    private String graphContent;
    /**
     * 触发方式
     */
    private String targetWay;
}
