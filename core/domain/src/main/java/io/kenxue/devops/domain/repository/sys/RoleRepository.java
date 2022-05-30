package io.kenxue.devops.domain.repository.sys;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.role.RolePageQry;
import io.kenxue.devops.domain.domain.sys.Role;

import java.util.List;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
public interface RoleRepository {
    void create(Role role);
    void update(Role role);
    Role getById(Long id);
    Page<Role> page(RolePageQry qry);
    List<Role> list(String roleName);
}
