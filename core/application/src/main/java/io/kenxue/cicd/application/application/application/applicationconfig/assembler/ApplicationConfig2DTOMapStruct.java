package io.kenxue.cicd.application.application.application.applicationconfig.assembler;

import io.kenxue.cicd.coreclient.dto.application.applicationconfig.ApplicationConfigDTO;
import io.kenxue.cicd.domain.domain.application.ApplicationConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
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
