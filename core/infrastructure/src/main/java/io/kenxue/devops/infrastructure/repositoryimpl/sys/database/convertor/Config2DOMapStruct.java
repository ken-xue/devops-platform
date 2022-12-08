package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.devops.domain.domain.sys.Config;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.ConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Config2DOMapStruct {

    Config2DOMapStruct INSTANCE = Mappers.getMapper(Config2DOMapStruct.class);

    @Mappings({})
    ConfigDO toDO(Config config);

    @Mappings({})
    Config toDomain(ConfigDO configDO);

    List<ConfigDO> toDOList(List<Config> configList);

    List<Config> toDomainList(List<ConfigDO> configDOList);
}
