package io.kenxue.devops.coreclient.dto.project.taskofuser;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskOfUserDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
