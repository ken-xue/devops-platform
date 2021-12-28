package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.ApplicationConfig;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationConfig2DOMapStruct {

    ApplicationConfig2DOMapStruct INSTANCE = Mappers.getMapper(ApplicationConfig2DOMapStruct.class);

    @Mappings({})
    ApplicationConfigDO toDO(ApplicationConfig applicationConfig);

    @Mappings({})
    ApplicationConfig toDomain(ApplicationConfigDO applicationConfigDO);

    List<ApplicationConfigDO> toDOList(List<ApplicationConfig> applicationConfigList);

    List<ApplicationConfig> toDomainList(List<ApplicationConfigDO> applicationConfigDOList);
}
