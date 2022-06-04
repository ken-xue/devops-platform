package io.kenxue.devops.application.project.task.assembler;

import io.kenxue.devops.coreclient.dto.project.task.TaskDTO;
import io.kenxue.devops.domain.domain.project.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Task2DTOMapStruct {

    Task2DTOMapStruct INSTANCE = Mappers.getMapper(Task2DTOMapStruct.class);

    @Mappings({})
    TaskDTO toDTO(Task task);

    @Mappings({})
    Task toDomain(TaskDTO taskDTO);

    List<TaskDTO> toDTOList(List<Task> TaskList);

    List<Task> toDomainList(List<TaskDTO> TaskDTOList);
}
