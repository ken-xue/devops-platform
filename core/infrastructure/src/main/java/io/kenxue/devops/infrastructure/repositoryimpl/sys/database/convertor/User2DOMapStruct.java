package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.devops.domain.domain.sys.User;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface User2DOMapStruct {

    User2DOMapStruct INSTANCE = Mappers.getMapper(User2DOMapStruct.class);

    @Mappings({})
    UserDO toDO(User user);


    List<UserDO> toDOList(List<User> userList);

    @Mappings({})
    User toDomain(UserDO userDO);


    List<User> toDomainList(List<UserDO> userDOList);
}
