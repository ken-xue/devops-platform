package io.kenxue.devops.application.application.config.assembler;

import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigDTO;
import io.kenxue.devops.domain.domain.application.ApplicationConfig;
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
public interface ApplicationConfig2DTOMapStruct {

    ApplicationConfig2DTOMapStruct INSTANCE = Mappers.getMapper(ApplicationConfig2DTOMapStruct.class);

    @Mappings({})
    ApplicationConfigDTO toDTO(ApplicationConfig applicationConfig);

    @Mappings({})
    ApplicationConfig toDomain(ApplicationConfigDTO applicationConfigDTO);

    List<ApplicationConfigDTO> toDTOList(List<ApplicationConfig> ApplicationConfigList);

    List<ApplicationConfig> toDomainList(List<ApplicationConfigDTO> ApplicationConfigDTOList);
}
