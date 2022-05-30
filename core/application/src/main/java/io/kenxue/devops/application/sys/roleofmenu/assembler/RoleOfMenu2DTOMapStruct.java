package io.kenxue.devops.application.sys.roleofmenu.assembler;

import io.kenxue.devops.coreclient.dto.sys.roleofmenu.RoleOfMenuDTO;
import io.kenxue.devops.domain.domain.sys.RoleOfMenu;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-12-03 17:39:58
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleOfMenu2DTOMapStruct {

    RoleOfMenu2DTOMapStruct INSTANCE = Mappers.getMapper(RoleOfMenu2DTOMapStruct.class);

    @Mappings({})
    RoleOfMenuDTO toDTO(RoleOfMenu roleOfMenu);


    List<RoleOfMenuDTO> toDTOList(List<RoleOfMenu> roleOfMenuList);

    @Mappings({})
    RoleOfMenu toDomain(RoleOfMenuDTO roleOfMenuDTO);


    List<RoleOfMenu> toDomainList(List<RoleOfMenuDTO> roleOfMenuDTOList);
}
