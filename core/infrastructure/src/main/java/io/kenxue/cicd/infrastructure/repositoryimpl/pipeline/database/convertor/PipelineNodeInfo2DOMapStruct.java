package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.convertor;

import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject.PipelineNodeInfoDO;
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
public interface PipelineNodeInfo2DOMapStruct {

    PipelineNodeInfo2DOMapStruct INSTANCE = Mappers.getMapper(PipelineNodeInfo2DOMapStruct.class);

    @Mappings({})
    PipelineNodeInfoDO toDO(PipelineNodeInfo pipelineNodeInfo);

    @Mappings({})
    PipelineNodeInfo toDomain(PipelineNodeInfoDO pipelineNodeInfoDO);

    List<PipelineNodeInfoDO> toDOList(List<PipelineNodeInfo> pipelineNodeInfoList);

    List<PipelineNodeInfo> toDomainList(List<PipelineNodeInfoDO> pipelineNodeInfoDOList);
}
