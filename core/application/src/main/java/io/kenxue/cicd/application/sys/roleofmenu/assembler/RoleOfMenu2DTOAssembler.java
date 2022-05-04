package io.kenxue.cicd.application.sys.roleofmenu.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuDTO;
import io.kenxue.cicd.domain.domain.sys.RoleOfMenu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-12-03 17:39:58
 */
@Component
public class RoleOfMenu2DTOAssembler implements Assembler<RoleOfMenuDTO, RoleOfMenu> {

    public RoleOfMenuDTO toDTO(RoleOfMenu roleOfMenu) {
        return RoleOfMenu2DTOMapStruct.INSTANCE.toDTO(roleOfMenu);
    }

    public RoleOfMenu toDomain(RoleOfMenuDTO roleOfMenuDTO) {
        return RoleOfMenu2DTOMapStruct.INSTANCE.toDomain(roleOfMenuDTO);
    }

    @Override
    public Page<RoleOfMenuDTO> toDTOPage(Page<RoleOfMenu> page) {
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), RoleOfMenu2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }

    public List<RoleOfMenuDTO> toDTOList(List<RoleOfMenu> roleOfMenuList) {
        return RoleOfMenu2DTOMapStruct.INSTANCE.toDTOList(roleOfMenuList);
    }

    @Override
    public List<RoleOfMenu> toDomainList(List<RoleOfMenuDTO> dTOList) {
        return RoleOfMenu2DTOMapStruct.INSTANCE.toDomainList(dTOList);
    }
}
