package io.kenxue.devops.application.project.taskfile.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileDTO;
import io.kenxue.devops.domain.domain.project.TaskFile;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskFile2DTOAssembler implements Assembler<TaskFileDTO, TaskFile> {

    @Override
    public TaskFileDTO toDTO(TaskFile taskFile) {
        return TaskFile2DTOMapStruct.INSTANCE.toDTO(taskFile);
    }

    @Override
    public TaskFile toDomain(TaskFileDTO taskFileDTO) {
        return TaskFile2DTOMapStruct.INSTANCE.toDomain(taskFileDTO);
    }

    @Override
    public List<TaskFileDTO> toDTOList(List<TaskFile> taskFileList) {
        return TaskFile2DTOMapStruct.INSTANCE.toDTOList(taskFileList);
    }

    @Override
    public List<TaskFile> toDomainList(List<TaskFileDTO> taskFileDTOList) {
        return TaskFile2DTOMapStruct.INSTANCE.toDomainList(taskFileDTOList);
    }

    @Override
    public Page<TaskFileDTO> toDTOPage(Page<TaskFile> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), TaskFile2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
