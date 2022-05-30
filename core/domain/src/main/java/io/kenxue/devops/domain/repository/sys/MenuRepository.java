package io.kenxue.devops.domain.repository.sys;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.menu.MenuGetSelectQry;
import io.kenxue.devops.coreclient.dto.sys.menu.MenuPageQry;
import io.kenxue.devops.domain.domain.sys.Menu;

import java.util.List;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
public interface MenuRepository {
    void create(Menu menu);
    void update(Menu menu);
    Menu getById(Long id);
    List<Menu> getByParentUuid(String uuid);
    List<Menu> list(String menuParentUuid);
    Page<Menu> page(MenuPageQry qry);
    List<Menu> listForSelect(MenuGetSelectQry qry);
}
