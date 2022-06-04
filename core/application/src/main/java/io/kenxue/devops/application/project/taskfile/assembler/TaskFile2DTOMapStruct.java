package io.kenxue.devops.application.project.taskfile.assembler;

import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileDTO;
import io.kenxue.devops.domain.domain.project.TaskFile;
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
public interface TaskFile2DTOMapStruct {

    TaskFile2DTOMapStruct INSTANCE = Mappers.getMapper(TaskFile2DTOMapStruct.class);

    @Mappings({})
    TaskFileDTO toDTO(TaskFile taskFile);

    @Mappings({})
    TaskFile toDomain(TaskFileDTO taskFileDTO);

    List<TaskFileDTO> toDTOList(List<TaskFile> TaskFileList);

    List<TaskFile> toDomainList(List<TaskFileDTO> TaskFileDTOList);
}
