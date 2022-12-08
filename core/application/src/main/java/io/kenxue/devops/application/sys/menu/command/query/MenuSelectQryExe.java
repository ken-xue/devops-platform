package io.kenxue.devops.application.sys.menu.command.query;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.sys.menu.MenuGetSelectQry;
import io.kenxue.devops.domain.domain.sys.Menu;
import io.kenxue.devops.domain.factory.sys.MenuFactory;
import io.kenxue.devops.domain.repository.sys.MenuRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor.Menu2DOConvector;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
public class MenuSelectQryExe {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private Menu2DOConvector menu2DOConvector;

    public MultiResponse execute(MenuGetSelectQry qry) {
        List<Menu> list = menuRepository.listForSelect(qry);
        if (Objects.nonNull(qry.getNeedRoot())&&qry.getNeedRoot()) {
            Menu menu = MenuFactory.getMenu();
            menu.setMenuName("一级菜单");
            menu.setMenuType(0);
            menu.setUuid("0");
            list.add(menu);
        }
        return MultiResponse.of(menu2DOConvector.toDOList(list));
    }
}

