package io.kenxue.devops.coreclient.dto.application.applicationofuser;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationOfUserListQry extends CommonCommand {
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
}
