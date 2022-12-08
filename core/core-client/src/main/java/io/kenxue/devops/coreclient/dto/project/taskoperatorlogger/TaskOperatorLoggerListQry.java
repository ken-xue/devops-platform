package io.kenxue.devops.coreclient.dto.project.taskoperatorlogger;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskOperatorLoggerListQry extends CommonCommand {
    @Schema(description = "项目名称")
    private String taskUuid;
    @Schema(description = "操作人")
    private String userUuid;
    @Schema(description = "内容")
    private String content;
}
