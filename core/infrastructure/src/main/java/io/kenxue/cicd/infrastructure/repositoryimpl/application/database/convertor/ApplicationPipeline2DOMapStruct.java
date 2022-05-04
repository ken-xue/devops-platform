package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationPipelineDO;
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
public interface ApplicationPipeline2DOMapStruct {

    ApplicationPipeline2DOMapStruct INSTANCE = Mappers.getMapper(ApplicationPipeline2DOMapStruct.class);

    @Mappings({})
    ApplicationPipelineDO toDO(Pipeline pipeline);

    @Mappings({})
    Pipeline toDomain(ApplicationPipelineDO applicationPipelineDO);

    List<ApplicationPipelineDO> toDOList(List<Pipeline> pipelineList);

    List<Pipeline> toDomainList(List<ApplicationPipelineDO> applicationPipelineDOList);
}
