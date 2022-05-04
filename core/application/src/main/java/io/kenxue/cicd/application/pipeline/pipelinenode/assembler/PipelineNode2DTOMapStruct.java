package io.kenxue.cicd.application.pipeline.pipelinenode.assembler;

import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenode.PipelineNodeDTO;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PipelineNode2DTOMapStruct {

    PipelineNode2DTOMapStruct INSTANCE = Mappers.getMapper(PipelineNode2DTOMapStruct.class);

    @Mappings({})
    PipelineNodeDTO toDTO(PipelineNode pipelineNode);

    @Mappings({})
    PipelineNode toDomain(PipelineNodeDTO pipelineNodeDTO);

    List<PipelineNodeDTO> toDTOList(List<PipelineNode> PipelineNodeList);

    List<PipelineNode> toDomainList(List<PipelineNodeDTO> PipelineNodeDTOList);
}
