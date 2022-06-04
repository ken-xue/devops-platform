package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskOperatorLoggerDO;
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
public interface TaskOperatorLogger2DOMapStruct {

    TaskOperatorLogger2DOMapStruct INSTANCE = Mappers.getMapper(TaskOperatorLogger2DOMapStruct.class);

    @Mappings({})
    TaskOperatorLoggerDO toDO(TaskOperatorLogger taskOperatorLogger);

    @Mappings({})
    TaskOperatorLogger toDomain(TaskOperatorLoggerDO taskOperatorLoggerDO);

    List<TaskOperatorLoggerDO> toDOList(List<TaskOperatorLogger> taskOperatorLoggerList);

    List<TaskOperatorLogger> toDomainList(List<TaskOperatorLoggerDO> taskOperatorLoggerDOList);
}
