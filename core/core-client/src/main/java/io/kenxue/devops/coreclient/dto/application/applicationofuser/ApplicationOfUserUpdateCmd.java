package io.kenxue.devops.coreclient.dto.application.applicationofuser;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationOfUserUpdateCmd extends CommonCommand {

    @NotNull
    private ApplicationOfUserDTO applicationOfUserDTO;
}
