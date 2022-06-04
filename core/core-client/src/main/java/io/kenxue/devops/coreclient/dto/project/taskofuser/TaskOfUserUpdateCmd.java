package io.kenxue.devops.coreclient.dto.project.taskofuser;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskOfUserUpdateCmd extends CommonCommand {

    @NotNull
    private TaskOfUserDTO taskOfUserDTO;
}
