package io.kenxue.devops.coreclient.dto.project.taskgroup;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Data
@Accessors(chain = true)
public class TaskGroupListQry extends CommonCommand {
    @ApiModelProperty(value = "项目uuid")
    @NotNull(message = "请传入项目uuid")
    private String projectUuid;
    @ApiModelProperty(value = "任务组名")
    private String groupName;
}
