package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.domain.domain.project.TaskGroup;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskGroupDO;
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
public interface TaskGroup2DOMapStruct {

    TaskGroup2DOMapStruct INSTANCE = Mappers.getMapper(TaskGroup2DOMapStruct.class);

    @Mappings({})
    TaskGroupDO toDO(TaskGroup taskGroup);

    @Mappings({})
    TaskGroup toDomain(TaskGroupDO taskGroupDO);

    List<TaskGroupDO> toDOList(List<TaskGroup> taskGroupList);

    List<TaskGroup> toDomainList(List<TaskGroupDO> taskGroupDOList);
}
