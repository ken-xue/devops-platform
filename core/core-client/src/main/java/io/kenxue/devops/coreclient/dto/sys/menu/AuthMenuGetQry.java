package io.kenxue.devops.coreclient.dto.sys.menu;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthMenuGetQry extends CommonCommand {
    private String userId;
}
