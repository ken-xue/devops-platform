package io.kenxue.cicd.coreclient.dto.sys.user;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UserFullGetQry extends CommonCommand {
    private String userId;
}