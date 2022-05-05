package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Data
@TableName("pipeline_node_execute_logger")
public class NodeExecuteLoggerDO extends CommonEntity {
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
