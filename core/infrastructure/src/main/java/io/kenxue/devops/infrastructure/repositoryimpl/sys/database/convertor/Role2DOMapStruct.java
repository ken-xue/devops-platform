package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.devops.domain.domain.sys.Role;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.RoleDO;
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
public interface Role2DOMapStruct {

    Role2DOMapStruct INSTANCE = Mappers.getMapper(Role2DOMapStruct.class);

    @Mappings({})
    RoleDO toDO(Role role);

    List<RoleDO> toDOList(List<Role> roleList);
    @Mappings({})
    Role toDomain(RoleDO roleDO);

    List<Role> toDomainList(List<RoleDO> roleDOList);
}
