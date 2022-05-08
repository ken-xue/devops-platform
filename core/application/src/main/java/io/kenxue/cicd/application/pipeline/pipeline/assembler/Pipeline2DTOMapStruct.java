package io.kenxue.cicd.application.pipeline.pipeline.assembler;

import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineDTO;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
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
public interface Pipeline2DTOMapStruct {

    Pipeline2DTOMapStruct INSTANCE = Mappers.getMapper(Pipeline2DTOMapStruct.class);

    @Mappings({})
    PipelineDTO toDTO(Pipeline pipeline);

    @Mappings({})
    Pipeline toDomain(PipelineDTO pipelineDTO);

    List<PipelineDTO> toDTOList(List<Pipeline> pipelineList);

    List<Pipeline> toDomainList(List<PipelineDTO> pipelineDTOList);
}
