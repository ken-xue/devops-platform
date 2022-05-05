package io.kenxue.cicd.application.application.pipeline.pipelineexecutelogger.assembler;

import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerDTO;
import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
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
public interface PipelineExecuteLogger2DTOMapStruct {

    PipelineExecuteLogger2DTOMapStruct INSTANCE = Mappers.getMapper(PipelineExecuteLogger2DTOMapStruct.class);

    @Mappings({})
    PipelineExecuteLoggerDTO toDTO(PipelineExecuteLogger pipelineExecuteLogger);

    @Mappings({})
    PipelineExecuteLogger toDomain(PipelineExecuteLoggerDTO pipelineExecuteLoggerDTO);

    List<PipelineExecuteLoggerDTO> toDTOList(List<PipelineExecuteLogger> PipelineExecuteLoggerList);

    List<PipelineExecuteLogger> toDomainList(List<PipelineExecuteLoggerDTO> PipelineExecuteLoggerDTOList);
}
