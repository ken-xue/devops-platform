package io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@TableName("project_task_of_user")
public class TaskOfUserDO extends CommonEntity {
    /**
     * 项目uuid
     */
    private String taskUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
}
