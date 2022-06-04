package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskWorkingHoursDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskWorkingHours2DOConvector implements Convector<TaskWorkingHours,TaskWorkingHoursDO>{
    
    public TaskWorkingHoursDO toDO(TaskWorkingHours taskWorkingHours) {
        return TaskWorkingHours2DOMapStruct.INSTANCE.toDO(taskWorkingHours);
    }

    public TaskWorkingHours toDomain(TaskWorkingHoursDO taskWorkingHoursDO) {
        return TaskWorkingHours2DOMapStruct.INSTANCE.toDomain(taskWorkingHoursDO);
    }

    public List<TaskWorkingHoursDO> toDOList(List<TaskWorkingHours> taskWorkingHoursList) {
        return TaskWorkingHours2DOMapStruct.INSTANCE.toDOList(taskWorkingHoursList);
    }

    public List<TaskWorkingHours> toDomainList(List<TaskWorkingHoursDO> taskWorkingHoursDOList) {
        return TaskWorkingHours2DOMapStruct.INSTANCE.toDomainList(taskWorkingHoursDOList);
    }
}
