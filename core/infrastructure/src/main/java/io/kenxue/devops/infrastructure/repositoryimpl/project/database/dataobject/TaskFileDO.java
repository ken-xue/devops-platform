package io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@TableName("project_task_file")
public class TaskFileDO extends CommonEntity {
    /**
     * 任务uuid
     */
    private String taskUuid;
    /**
     * 文件名称
     */
    private Date fileName;
    /**
     * 文件路径
     */
    private Date url;
}
