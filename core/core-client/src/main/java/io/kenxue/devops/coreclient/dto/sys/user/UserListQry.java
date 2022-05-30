package io.kenxue.devops.coreclient.dto.sys.user;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;

@Data
public class UserListQry extends CommonCommand {
    private String email;
    private String username;
}
