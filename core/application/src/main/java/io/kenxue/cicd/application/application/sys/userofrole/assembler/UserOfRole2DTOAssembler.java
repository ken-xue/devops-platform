package io.kenxue.cicd.application.application.sys.userofrole.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.userofrole.UserOfRoleDTO;
import io.kenxue.cicd.domain.domain.sys.UserOfRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-12-03 17:56:55
 */
@Component
public class UserOfRole2DTOAssembler implements Assembler<UserOfRoleDTO, UserOfRole> {

    public UserOfRoleDTO toDTO(UserOfRole userOfRole) {
        return UserOfRole2DTOMapStruct.INSTANCE.toDTO(userOfRole);
    }

    public UserOfRole toDomain(UserOfRoleDTO userOfRoleDTO) {
        return UserOfRole2DTOMapStruct.INSTANCE.toDomain(userOfRoleDTO);
    }

    @Override
    public List<UserOfRoleDTO> toDTOList(List<UserOfRole> userOfRoleList) {
        return UserOfRole2DTOMapStruct.INSTANCE.toDTOList(userOfRoleList);
    }

    @Override
    public List<UserOfRole> toDomainList(List<UserOfRoleDTO> dTOList) {
        return UserOfRole2DTOMapStruct.INSTANCE.toDomainList(dTOList);
    }

    @Override
    public Page<UserOfRoleDTO> toDTOPage(Page<UserOfRole> page) {
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), UserOfRole2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
