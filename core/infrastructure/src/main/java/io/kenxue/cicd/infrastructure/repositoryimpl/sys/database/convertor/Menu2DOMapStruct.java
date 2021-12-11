package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.MenuDO;
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
public interface Menu2DOMapStruct {

    Menu2DOMapStruct INSTANCE = Mappers.getMapper(Menu2DOMapStruct.class);

    @Mappings({})
    MenuDO toDO(Menu menu);

    @Mappings({})
    Menu toDomain(MenuDO menuDO);


    List<MenuDO> toDOList(List<Menu> menuList);


    List<Menu> toDomainList(List<MenuDO> menuDOList);
}
