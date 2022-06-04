package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.domain.domain.project.Task;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskDO;
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
public interface Task2DOMapStruct {

    Task2DOMapStruct INSTANCE = Mappers.getMapper(Task2DOMapStruct.class);

    @Mappings({})
    TaskDO toDO(Task task);

    @Mappings({})
    Task toDomain(TaskDO taskDO);

    List<TaskDO> toDOList(List<Task> taskList);

    List<Task> toDomainList(List<TaskDO> taskDOList);
}
