package io.kenxue.cicd.application.application.sys.menu.assembler;

import io.kenxue.cicd.coreclient.dto.sys.menu.MenuDTO;
import io.kenxue.cicd.domain.domain.sys.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 菜单表
 * @author mikey
 * @date 2021-12-03 17:18:36
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Menu2DTOMapStruct {

    Menu2DTOMapStruct INSTANCE = Mappers.getMapper(Menu2DTOMapStruct.class);

    @Mappings({})
    MenuDTO toDTO(Menu menu);


    List<MenuDTO> toDTOList(List<Menu> menuList);

    @Mappings({})
    Menu toDomain(MenuDTO menuCO);


    List<Menu> toDomainList(List<MenuDTO> menuDTOList);
}
