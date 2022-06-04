package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskOperatorLoggerDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOperatorLogger2DOConvector implements Convector<TaskOperatorLogger,TaskOperatorLoggerDO>{
    
    public TaskOperatorLoggerDO toDO(TaskOperatorLogger taskOperatorLogger) {
        return TaskOperatorLogger2DOMapStruct.INSTANCE.toDO(taskOperatorLogger);
    }

    public TaskOperatorLogger toDomain(TaskOperatorLoggerDO taskOperatorLoggerDO) {
        return TaskOperatorLogger2DOMapStruct.INSTANCE.toDomain(taskOperatorLoggerDO);
    }

    public List<TaskOperatorLoggerDO> toDOList(List<TaskOperatorLogger> taskOperatorLoggerList) {
        return TaskOperatorLogger2DOMapStruct.INSTANCE.toDOList(taskOperatorLoggerList);
    }

    public List<TaskOperatorLogger> toDomainList(List<TaskOperatorLoggerDO> taskOperatorLoggerDOList) {
        return TaskOperatorLogger2DOMapStruct.INSTANCE.toDomainList(taskOperatorLoggerDOList);
    }
}
