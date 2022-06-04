package io.kenxue.devops.coreclient.dto.project.taskworkinghours;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskWorkingHoursAddCmd extends CommonCommand {
    @NotNull
    private TaskWorkingHoursDTO taskWorkingHoursDTO;
}
