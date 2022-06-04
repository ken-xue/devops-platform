package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.domain.domain.project.Task;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Component
public class Task2DOConvector implements Convector<Task,TaskDO>{
    
    public TaskDO toDO(Task task) {
        return Task2DOMapStruct.INSTANCE.toDO(task);
    }

    public Task toDomain(TaskDO taskDO) {
        return Task2DOMapStruct.INSTANCE.toDomain(taskDO);
    }

    public List<TaskDO> toDOList(List<Task> taskList) {
        return Task2DOMapStruct.INSTANCE.toDOList(taskList);
    }

    public List<Task> toDomainList(List<TaskDO> taskDOList) {
        return Task2DOMapStruct.INSTANCE.toDomainList(taskDOList);
    }
}
