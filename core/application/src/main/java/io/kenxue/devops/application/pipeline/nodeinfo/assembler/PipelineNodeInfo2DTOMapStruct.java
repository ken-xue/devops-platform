package io.kenxue.devops.application.pipeline.nodeinfo.assembler;

import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoDTO;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PipelineNodeInfo2DTOMapStruct {

    PipelineNodeInfo2DTOMapStruct INSTANCE = Mappers.getMapper(PipelineNodeInfo2DTOMapStruct.class);

    @Mappings({})
    PipelineNodeInfoDTO toDTO(PipelineNodeInfo pipelineNodeInfo);

    @Mappings({})
    PipelineNodeInfo toDomain(PipelineNodeInfoDTO pipelineNodeInfoDTO);

    List<PipelineNodeInfoDTO> toDTOList(List<PipelineNodeInfo> PipelineNodeInfoList);

    List<PipelineNodeInfo> toDomainList(List<PipelineNodeInfoDTO> PipelineNodeInfoDTOList);
}
