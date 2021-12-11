package io.kenxue.cicd.application.application.sys.role.assembler;

import io.kenxue.cicd.coreclient.dto.sys.role.RoleDTO;
import io.kenxue.cicd.domain.domain.sys.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色表
 * @author mikey
 * @date 2021-12-03 17:27:04
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Role2DTOMapStruct {

    Role2DTOMapStruct INSTANCE = Mappers.getMapper(Role2DTOMapStruct.class);

    @Mappings({})
    RoleDTO toDTO(Role role);

    List<RoleDTO> toDTOList(List<Role> roleList);
    @Mappings({})
    Role toDomain(RoleDTO roleDTO);

    List<Role> toDomainList(List<RoleDTO> roleDTOList);
}
