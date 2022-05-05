package io.kenxue.cicd.domain.domain.pipeline;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PipelineExecuteLogger extends CommonEntity {
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
    /**
     * 最终执行状态
     */
    private String finalStatus;
}
