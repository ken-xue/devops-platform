package io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;

import java.util.Date;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@TableName("pipeline")
public class ApplicationPipelineDO extends CommonEntity {
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 流水线名称
     */
    private String pipelineName;
    /**
     * 应用类型
     */
    private String pipelineContext;
    /**
     * 描述
     */
    private String description;
    /**
     * 触发方式
     */
    private String triggerWay;
    /**
     * 最新触发时间
     */
    private Date latestTriggerTime;
    /**
     * 分支
     */
    private String branch;
}
