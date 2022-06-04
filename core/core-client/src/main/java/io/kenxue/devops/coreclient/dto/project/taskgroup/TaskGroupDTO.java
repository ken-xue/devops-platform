package io.kenxue.devops.coreclient.dto.project.taskgroup;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import io.kenxue.devops.coreclient.dto.project.task.TaskDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Data
@Accessors(chain = true)
public class TaskGroupDTO extends CommonDTO {
    @ApiModelProperty(value = "项目uuid")
    private String projectUuid;
    @ApiModelProperty(value = "任务组名")
    private String groupName;

    /**
     * 对应的任务
     */
    private List<TaskDTO> taskDTOList;
}
