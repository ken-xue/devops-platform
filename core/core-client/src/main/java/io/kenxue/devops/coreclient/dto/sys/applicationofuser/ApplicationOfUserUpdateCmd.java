package io.kenxue.devops.coreclient.dto.sys.applicationofuser;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationOfUserUpdateCmd extends CommonCommand {

    @NotNull
    private ApplicationOfUserDTO applicationOfUserDTO;
}
