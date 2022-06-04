package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskWorkingHoursDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskWorkingHours2DOMapStruct {

    TaskWorkingHours2DOMapStruct INSTANCE = Mappers.getMapper(TaskWorkingHours2DOMapStruct.class);

    @Mappings({})
    TaskWorkingHoursDO toDO(TaskWorkingHours taskWorkingHours);

    @Mappings({})
    TaskWorkingHours toDomain(TaskWorkingHoursDO taskWorkingHoursDO);

    List<TaskWorkingHoursDO> toDOList(List<TaskWorkingHours> taskWorkingHoursList);

    List<TaskWorkingHours> toDomainList(List<TaskWorkingHoursDO> taskWorkingHoursDOList);
}
