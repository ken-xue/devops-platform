package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationInfo2DOMapStruct {

    ApplicationInfo2DOMapStruct INSTANCE = Mappers.getMapper(ApplicationInfo2DOMapStruct.class);

    @Mappings({})
    ApplicationInfoDO toDO(ApplicationInfo applicationInfo);

    @Mappings({})
    ApplicationInfo toDomain(ApplicationInfoDO applicationInfoDO);

    List<ApplicationInfoDO> toDOList(List<ApplicationInfo> applicationInfoList);

    List<ApplicationInfo> toDomainList(List<ApplicationInfoDO> applicationInfoDOList);
}
