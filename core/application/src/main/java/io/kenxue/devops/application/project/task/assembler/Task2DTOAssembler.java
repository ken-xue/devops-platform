package io.kenxue.devops.application.project.task.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.task.TaskDTO;
import io.kenxue.devops.domain.domain.project.Task;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Component
public class Task2DTOAssembler implements Assembler<TaskDTO, Task> {

    @Override
    public TaskDTO toDTO(Task task) {
        return Task2DTOMapStruct.INSTANCE.toDTO(task);
    }

    @Override
    public Task toDomain(TaskDTO taskDTO) {
        return Task2DTOMapStruct.INSTANCE.toDomain(taskDTO);
    }

    @Override
    public List<TaskDTO> toDTOList(List<Task> taskList) {
        return Task2DTOMapStruct.INSTANCE.toDTOList(taskList);
    }

    @Override
    public List<Task> toDomainList(List<TaskDTO> taskDTOList) {
        return Task2DTOMapStruct.INSTANCE.toDomainList(taskDTOList);
    }

    @Override
    public Page<TaskDTO> toDTOPage(Page<Task> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Task2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
