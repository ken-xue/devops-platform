package io.kenxue.devops.domain.factory.sys;

import io.kenxue.devops.domain.domain.sys.Role;
/**
 * 角色表
 * @author mikey
 * @date 2021-12-03 17:27:04
 */
public class RoleFactory {
    public static Role getRole(){
        return new Role();
    }
}
