package io.kenxue.cicd.coreclient.dto.sys.user;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;

@Data
public class UserListQry extends CommonCommand {
    private String email;
    private String username;
}
