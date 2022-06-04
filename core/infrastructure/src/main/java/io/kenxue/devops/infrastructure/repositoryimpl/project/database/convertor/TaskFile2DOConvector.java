package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.domain.domain.project.TaskFile;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskFileDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskFile2DOConvector implements Convector<TaskFile,TaskFileDO>{
    
    public TaskFileDO toDO(TaskFile taskFile) {
        return TaskFile2DOMapStruct.INSTANCE.toDO(taskFile);
    }

    public TaskFile toDomain(TaskFileDO taskFileDO) {
        return TaskFile2DOMapStruct.INSTANCE.toDomain(taskFileDO);
    }

    public List<TaskFileDO> toDOList(List<TaskFile> taskFileList) {
        return TaskFile2DOMapStruct.INSTANCE.toDOList(taskFileList);
    }

    public List<TaskFile> toDomainList(List<TaskFileDO> taskFileDOList) {
        return TaskFile2DOMapStruct.INSTANCE.toDomainList(taskFileDOList);
    }
}
