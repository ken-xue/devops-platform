package io.kenxue.cicd.application.sys.menu.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuDTO;
import io.kenxue.cicd.domain.domain.sys.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 菜单表
 * @author mikey
 * @date 2021-12-03 17:18:36
 */
@Component
public class Menu2DTOAssembler implements Assembler<MenuDTO, Menu> {

    @Override
    public MenuDTO toDTO(Menu menu) {
        return Menu2DTOMapStruct.INSTANCE.toDTO(menu);
    }

    @Override
    public Menu toDomain(MenuDTO menuDTO) {
        return Menu2DTOMapStruct.INSTANCE.toDomain(menuDTO);
    }

    @Override
    public List<MenuDTO> toDTOList(List<Menu> menuList) {
        return Menu2DTOMapStruct.INSTANCE.toDTOList(menuList);
    }

    @Override
    public List<Menu> toDomainList(List<MenuDTO> dTOList) {
        return Menu2DTOMapStruct.INSTANCE.toDomainList(dTOList);
    }

    @Override
    public Page<MenuDTO> toDTOPage(Page<Menu> page) {
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Menu2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
