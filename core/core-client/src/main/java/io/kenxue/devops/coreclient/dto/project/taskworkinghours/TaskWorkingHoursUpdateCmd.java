package io.kenxue.devops.coreclient.dto.project.taskworkinghours;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskWorkingHoursUpdateCmd extends CommonCommand {

    @NotNull
    private TaskWorkingHoursDTO taskWorkingHoursDTO;
}
