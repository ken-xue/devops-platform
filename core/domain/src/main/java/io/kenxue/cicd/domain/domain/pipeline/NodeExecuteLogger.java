package io.kenxue.cicd.domain.domain.pipeline;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeExecuteLogger extends CommonEntity {
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
}
