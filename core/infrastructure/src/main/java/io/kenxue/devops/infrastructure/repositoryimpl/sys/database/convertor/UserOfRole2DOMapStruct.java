package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.devops.domain.domain.sys.UserOfRole;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.UserOfRoleDO;
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
public interface UserOfRole2DOMapStruct {

    UserOfRole2DOMapStruct INSTANCE = Mappers.getMapper(UserOfRole2DOMapStruct.class);

    @Mappings({})
    UserOfRoleDO toDO(UserOfRole userOfRole);

    List<UserOfRoleDO> toDOList(List<UserOfRole> userOfRoleList);

    @Mappings({})
    UserOfRole toDomain(UserOfRoleDO userOfRoleDO);

    List<UserOfRole> toDomainList(List<UserOfRoleDO> userOfRoleDOList);
}
