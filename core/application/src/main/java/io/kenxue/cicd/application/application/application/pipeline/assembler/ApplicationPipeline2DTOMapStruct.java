package io.kenxue.cicd.application.application.application.pipeline.assembler;

import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineDTO;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
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
public interface ApplicationPipeline2DTOMapStruct {

    ApplicationPipeline2DTOMapStruct INSTANCE = Mappers.getMapper(ApplicationPipeline2DTOMapStruct.class);

    @Mappings({})
    ApplicationPipelineDTO toDTO(ApplicationPipeline applicationPipeline);

    @Mappings({})
    ApplicationPipeline toDomain(ApplicationPipelineDTO applicationPipelineDTO);

    List<ApplicationPipelineDTO> toDTOList(List<ApplicationPipeline> ApplicationPipelineList);

    List<ApplicationPipeline> toDomainList(List<ApplicationPipelineDTO> ApplicationPipelineDTOList);
}
