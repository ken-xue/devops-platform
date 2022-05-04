package io.kenxue.cicd.application.sys.role.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.role.RoleDTO;
import io.kenxue.cicd.domain.domain.sys.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色表
 * @author mikey
 * @date 2021-12-03 17:27:04
 */
@Component
public class Role2DTOAssembler implements Assembler<RoleDTO, Role> {

    @Override
    public RoleDTO toDTO(Role role) {
        return Role2DTOMapStruct.INSTANCE.toDTO(role);
    }

    @Override
    public Role toDomain(RoleDTO roleDTO) {
        return Role2DTOMapStruct.INSTANCE.toDomain(roleDTO);
    }

    @Override
    public Page<RoleDTO> toDTOPage(Page<Role> page) {
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Role2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }

    @Override
    public List<RoleDTO> toDTOList(List<Role> list) {
        return Role2DTOMapStruct.INSTANCE.toDTOList(list);
    }

    @Override
    public List<Role> toDomainList(List<RoleDTO> dTOList) {
        return Role2DTOMapStruct.INSTANCE.toDomainList(dTOList);
    }
}
