package io.kenxue.devops.coreclient.dto.sys.user;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;

/**
 * @Author: 麦奇
 * @Date: 21-11-14 上午12:22
 */
@Data
public class UserPageQry extends PageQuery {

    private UserDTO userDTO;

}
