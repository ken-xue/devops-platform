package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.domain.domain.project.TaskFile;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskFileDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskFile2DOMapStruct {

    TaskFile2DOMapStruct INSTANCE = Mappers.getMapper(TaskFile2DOMapStruct.class);

    @Mappings({})
    TaskFileDO toDO(TaskFile taskFile);

    @Mappings({})
    TaskFile toDomain(TaskFileDO taskFileDO);

    List<TaskFileDO> toDOList(List<TaskFile> taskFileList);

    List<TaskFile> toDomainList(List<TaskFileDO> taskFileDOList);
}
