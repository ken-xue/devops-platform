package io.kenxue.devops.domain.factory.sys;

import io.kenxue.devops.domain.domain.sys.UserOfRole;
/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-12-03 17:56:55
 */
public class UserOfRoleFactory {
    public static UserOfRole getUserOfRole(){
        return new UserOfRole();
    }
}
