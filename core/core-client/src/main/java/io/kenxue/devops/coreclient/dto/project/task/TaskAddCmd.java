package io.kenxue.devops.coreclient.dto.project.task;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Data
@Accessors(chain = true)
public class TaskAddCmd extends CommonCommand {
    @NotNull
    private TaskDTO taskDTO;
}
