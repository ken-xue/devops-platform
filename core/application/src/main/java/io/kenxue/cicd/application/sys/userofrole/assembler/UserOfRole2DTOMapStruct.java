package io.kenxue.cicd.application.sys.userofrole.assembler;

import io.kenxue.cicd.coreclient.dto.sys.userofrole.UserOfRoleDTO;
import io.kenxue.cicd.domain.domain.sys.UserOfRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-12-03 17:56:55
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserOfRole2DTOMapStruct {

    UserOfRole2DTOMapStruct INSTANCE = Mappers.getMapper(UserOfRole2DTOMapStruct.class);

    @Mappings({})
    UserOfRoleDTO toDTO(UserOfRole userOfRole);

    @Mappings({})
    UserOfRole toDomain(UserOfRoleDTO userOfRoleDTO);


    List<UserOfRole> toDomainList(List<UserOfRoleDTO> userOfRoleDTOList);


    List<UserOfRoleDTO> toDTOList(List<UserOfRole> userOfRoleDTOList);
}
