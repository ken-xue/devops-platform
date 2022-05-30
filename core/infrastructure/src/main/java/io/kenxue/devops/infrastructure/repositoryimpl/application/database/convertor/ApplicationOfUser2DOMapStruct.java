package io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationOfUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationOfUser2DOMapStruct {

    ApplicationOfUser2DOMapStruct INSTANCE = Mappers.getMapper(ApplicationOfUser2DOMapStruct.class);

    @Mappings({})
    ApplicationOfUserDO toDO(ApplicationOfUser applicationOfUser);

    @Mappings({})
    ApplicationOfUser toDomain(ApplicationOfUserDO applicationOfUserDO);

    List<ApplicationOfUserDO> toDOList(List<ApplicationOfUser> applicationOfUserList);

    List<ApplicationOfUser> toDomainList(List<ApplicationOfUserDO> applicationOfUserDOList);
}
