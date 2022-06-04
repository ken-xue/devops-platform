package io.kenxue.devops.coreclient.dto.project.taskgroup;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Data
@Accessors(chain = true)
public class TaskGroupGetQry extends CommonCommand {

    private Long id;
    @ApiModelProperty(value = "项目uuid")
    private String projectUuid;
    @ApiModelProperty(value = "任务组名")
    private String groupName;
}