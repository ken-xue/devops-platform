package io.kenxue.devops.domain.repository.sys;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.roleofmenu.RoleOfMenuListQry;
import io.kenxue.devops.coreclient.dto.sys.roleofmenu.RoleOfMenuPageQry;
import io.kenxue.devops.domain.domain.sys.Menu;
import io.kenxue.devops.domain.domain.sys.RoleOfMenu;

import java.util.List;
import java.util.Set;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
public interface RoleOfMenuRepository {
    void create(RoleOfMenu roleOfMenu);
    void update(RoleOfMenu roleOfMenu);
    RoleOfMenu getById(long id);
    //查询所关联的菜单
    Set<Menu> getMenuByRoleUuids(List<String> roleUuids);
    List<RoleOfMenu> getRoleOfMenu(String roleUuid);
    void deleteByRoleUuid(String roleUuid);

    List<RoleOfMenu> list(RoleOfMenuListQry qry);

    Page<RoleOfMenu> page(RoleOfMenuPageQry qry);
}
