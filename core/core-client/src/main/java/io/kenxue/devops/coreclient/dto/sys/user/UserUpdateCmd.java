package io.kenxue.devops.coreclient.dto.sys.user;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class UserUpdateCmd extends CommonCommand {

    @NotNull
    private UserDTO userCO;
    private String bCryptPassword;
}
