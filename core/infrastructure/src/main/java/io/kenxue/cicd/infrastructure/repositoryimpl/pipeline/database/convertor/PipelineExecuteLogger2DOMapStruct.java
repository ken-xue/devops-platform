package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.convertor;

import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject.PipelineExecuteLoggerDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PipelineExecuteLogger2DOMapStruct {

    PipelineExecuteLogger2DOMapStruct INSTANCE = Mappers.getMapper(PipelineExecuteLogger2DOMapStruct.class);

    @Mappings({})
    PipelineExecuteLoggerDO toDO(PipelineExecuteLogger pipelineExecuteLogger);

    @Mappings({})
    PipelineExecuteLogger toDomain(PipelineExecuteLoggerDO pipelineExecuteLoggerDO);

    List<PipelineExecuteLoggerDO> toDOList(List<PipelineExecuteLogger> pipelineExecuteLoggerList);

    List<PipelineExecuteLogger> toDomainList(List<PipelineExecuteLoggerDO> pipelineExecuteLoggerDOList);
}
