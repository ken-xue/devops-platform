package io.kenxue.devops.coreclient.dto.sys.applicationofuser;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationOfUserDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
