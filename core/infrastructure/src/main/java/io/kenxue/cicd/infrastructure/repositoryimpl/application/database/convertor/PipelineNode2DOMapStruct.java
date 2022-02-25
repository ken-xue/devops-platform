package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.PipelineNode;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.PipelineNodeDO;
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
public interface PipelineNode2DOMapStruct {

    PipelineNode2DOMapStruct INSTANCE = Mappers.getMapper(PipelineNode2DOMapStruct.class);

    @Mappings({})
    PipelineNodeDO toDO(PipelineNode pipelineNode);

    @Mappings({})
    PipelineNode toDomain(PipelineNodeDO pipelineNodeDO);

    List<PipelineNodeDO> toDOList(List<PipelineNode> pipelineNodeList);

    List<PipelineNode> toDomainList(List<PipelineNodeDO> pipelineNodeDOList);
}
