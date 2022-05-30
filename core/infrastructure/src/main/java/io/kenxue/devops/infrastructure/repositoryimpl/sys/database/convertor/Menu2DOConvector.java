package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.devops.domain.domain.sys.Menu;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.MenuDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 菜单表
 * @author mikey
 * @date 2021-12-03 17:22:00
 */
@Component
public class Menu2DOConvector implements Convector<Menu,MenuDO> {

    @Override
    public MenuDO toDO(Menu menu) {
        return Menu2DOMapStruct.INSTANCE.toDO(menu);
    }

    @Override
    public Menu toDomain(MenuDO menuDO) {
        return Menu2DOMapStruct.INSTANCE.toDomain(menuDO);
    }

    @Override
    public List<MenuDO> toDOList(List<Menu> menuList) {
        return Menu2DOMapStruct.INSTANCE.toDOList(menuList);
    }

    @Override
    public List<Menu> toDomainList(List<MenuDO> menuDOList) {
        return Menu2DOMapStruct.INSTANCE.toDomainList(menuDOList);
    }
}
