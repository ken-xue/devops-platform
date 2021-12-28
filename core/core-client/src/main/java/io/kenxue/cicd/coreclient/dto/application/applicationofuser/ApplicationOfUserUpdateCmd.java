package io.kenxue.cicd.coreclient.dto.application.applicationofuser;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
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
