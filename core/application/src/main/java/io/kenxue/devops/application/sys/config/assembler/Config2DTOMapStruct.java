package io.kenxue.devops.application.sys.config.assembler;

import io.kenxue.devops.coreclient.dto.sys.config.ConfigDTO;
import io.kenxue.devops.domain.domain.sys.Config;
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
public interface Config2DTOMapStruct {

    Config2DTOMapStruct INSTANCE = Mappers.getMapper(Config2DTOMapStruct.class);

    @Mappings({})
    ConfigDTO toDTO(Config config);

    @Mappings({})
    Config toDomain(ConfigDTO configDTO);

    List<ConfigDTO> toDTOList(List<Config> ConfigList);

    List<Config> toDomainList(List<ConfigDTO> ConfigDTOList);
}
