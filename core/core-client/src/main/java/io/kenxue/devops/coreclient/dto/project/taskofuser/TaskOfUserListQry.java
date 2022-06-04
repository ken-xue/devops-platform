package io.kenxue.devops.coreclient.dto.project.taskofuser;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskOfUserListQry extends CommonCommand {
    @ApiModelProperty(value = "项目uuid")
    private String taskUuid;
    @ApiModelProperty(value = "用户uuid")
    private String userUuid;
}
