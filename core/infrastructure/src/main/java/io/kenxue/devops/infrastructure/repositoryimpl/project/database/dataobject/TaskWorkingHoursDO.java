package io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@TableName("project_task_working_hours")
public class TaskWorkingHoursDO extends CommonEntity {
    /**
     * 任务uuid
     */
    private String taskUuid;
    /**
     * 任务开始时间
     */
    private Date startTime;
    /**
     * 任务结束时间
     */
    private Date endTime;
    /**
     * 任务详细内容（富文本）
     */
    private String workDesc;
}
