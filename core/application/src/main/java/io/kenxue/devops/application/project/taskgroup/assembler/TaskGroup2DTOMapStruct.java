package io.kenxue.devops.application.project.taskgroup.assembler;

import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupDTO;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskGroup2DTOMapStruct {

    TaskGroup2DTOMapStruct INSTANCE = Mappers.getMapper(TaskGroup2DTOMapStruct.class);

    @Mappings({})
    TaskGroupDTO toDTO(TaskGroup taskGroup);

    @Mappings({})
    TaskGroup toDomain(TaskGroupDTO taskGroupDTO);

    List<TaskGroupDTO> toDTOList(List<TaskGroup> TaskGroupList);

    List<TaskGroup> toDomainList(List<TaskGroupDTO> TaskGroupDTOList);
}
