package io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Data
@TableName("project_task_group")
public class TaskGroupDO extends CommonEntity {
    /**
     * 项目uuid
     */
    private String projectUuid;
    /**
     * 任务组名
     */
    private String groupName;
}
