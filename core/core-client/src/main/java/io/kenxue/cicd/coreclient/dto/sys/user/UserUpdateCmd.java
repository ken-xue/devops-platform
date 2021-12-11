package io.kenxue.cicd.coreclient.dto.sys.user;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateCmd extends CommonCommand {

    @NotNull
    private UserDTO userCO;
    private String bCryptPassword;
}
