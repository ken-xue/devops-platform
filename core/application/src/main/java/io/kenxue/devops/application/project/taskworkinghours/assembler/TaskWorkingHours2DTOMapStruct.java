package io.kenxue.devops.application.project.taskworkinghours.assembler;

import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursDTO;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
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
public interface TaskWorkingHours2DTOMapStruct {

    TaskWorkingHours2DTOMapStruct INSTANCE = Mappers.getMapper(TaskWorkingHours2DTOMapStruct.class);

    @Mappings({})
    TaskWorkingHoursDTO toDTO(TaskWorkingHours taskWorkingHours);

    @Mappings({})
    TaskWorkingHours toDomain(TaskWorkingHoursDTO taskWorkingHoursDTO);

    List<TaskWorkingHoursDTO> toDTOList(List<TaskWorkingHours> TaskWorkingHoursList);

    List<TaskWorkingHours> toDomainList(List<TaskWorkingHoursDTO> TaskWorkingHoursDTOList);
}
