package io.kenxue.cicd.application.sys.menu.command.query;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuGetSelectQry;
import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.domain.factory.sys.MenuFactory;
import io.kenxue.cicd.domain.repository.sys.MenuRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Menu2DOConvector;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

