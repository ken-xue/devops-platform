package io.kenxue.devops.coreclient.dto.project.projectofuser;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@Accessors(chain = true)
public class ProjectOfUserUpdateCmd extends CommonCommand {

    @NotNull
    private ProjectOfUserDTO projectOfUserDTO;
}
