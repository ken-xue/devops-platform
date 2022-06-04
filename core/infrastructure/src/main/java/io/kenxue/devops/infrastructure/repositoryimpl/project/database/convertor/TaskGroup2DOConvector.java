package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskGroupDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Component
public class TaskGroup2DOConvector implements Convector<TaskGroup,TaskGroupDO>{
    
    public TaskGroupDO toDO(TaskGroup taskGroup) {
        return TaskGroup2DOMapStruct.INSTANCE.toDO(taskGroup);
    }

    public TaskGroup toDomain(TaskGroupDO taskGroupDO) {
        return TaskGroup2DOMapStruct.INSTANCE.toDomain(taskGroupDO);
    }

    public List<TaskGroupDO> toDOList(List<TaskGroup> taskGroupList) {
        return TaskGroup2DOMapStruct.INSTANCE.toDOList(taskGroupList);
    }

    public List<TaskGroup> toDomainList(List<TaskGroupDO> taskGroupDOList) {
        return TaskGroup2DOMapStruct.INSTANCE.toDomainList(taskGroupDOList);
    }
}
