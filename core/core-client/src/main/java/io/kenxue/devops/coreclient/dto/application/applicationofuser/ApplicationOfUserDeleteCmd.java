package io.kenxue.devops.coreclient.dto.application.applicationofuser;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationOfUserDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
