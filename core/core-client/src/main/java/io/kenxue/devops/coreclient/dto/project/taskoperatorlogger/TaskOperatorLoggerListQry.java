package io.kenxue.devops.coreclient.dto.project.taskoperatorlogger;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskOperatorLoggerListQry extends CommonCommand {
    @ApiModelProperty(value = "项目名称")
    private String taskUuid;
    @ApiModelProperty(value = "操作人")
    private String userUuid;
    @ApiModelProperty(value = "内容")
    private String content;
}
