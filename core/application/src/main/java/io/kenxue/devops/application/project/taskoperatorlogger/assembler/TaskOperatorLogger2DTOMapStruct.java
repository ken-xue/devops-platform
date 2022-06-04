package io.kenxue.devops.application.project.taskoperatorlogger.assembler;

import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerDTO;
import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskOperatorLogger2DTOMapStruct {

    TaskOperatorLogger2DTOMapStruct INSTANCE = Mappers.getMapper(TaskOperatorLogger2DTOMapStruct.class);

    @Mappings({})
    TaskOperatorLoggerDTO toDTO(TaskOperatorLogger taskOperatorLogger);

    @Mappings({})
    TaskOperatorLogger toDomain(TaskOperatorLoggerDTO taskOperatorLoggerDTO);

    List<TaskOperatorLoggerDTO> toDTOList(List<TaskOperatorLogger> TaskOperatorLoggerList);

    List<TaskOperatorLogger> toDomainList(List<TaskOperatorLoggerDTO> TaskOperatorLoggerDTOList);
}
