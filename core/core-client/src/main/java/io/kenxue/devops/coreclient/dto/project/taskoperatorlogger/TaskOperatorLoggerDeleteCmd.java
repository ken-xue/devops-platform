package io.kenxue.devops.coreclient.dto.project.taskoperatorlogger;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;

/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskOperatorLoggerDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
