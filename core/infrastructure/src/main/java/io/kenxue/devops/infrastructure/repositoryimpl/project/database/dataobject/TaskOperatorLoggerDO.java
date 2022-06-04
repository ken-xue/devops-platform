package io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@TableName("project_task_operator_logger")
public class TaskOperatorLoggerDO extends CommonEntity {
    /**
     * 项目名称
     */
    private String taskUuid;
    /**
     * 操作人
     */
    private String userUuid;
    /**
     * 内容
     */
    private String content;
}
