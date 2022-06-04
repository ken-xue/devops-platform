package io.kenxue.devops.application.project.taskgroup.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupDTO;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Component
public class TaskGroup2DTOAssembler implements Assembler<TaskGroupDTO, TaskGroup> {

    @Override
    public TaskGroupDTO toDTO(TaskGroup taskGroup) {
        return TaskGroup2DTOMapStruct.INSTANCE.toDTO(taskGroup);
    }

    @Override
    public TaskGroup toDomain(TaskGroupDTO taskGroupDTO) {
        return TaskGroup2DTOMapStruct.INSTANCE.toDomain(taskGroupDTO);
    }

    @Override
    public List<TaskGroupDTO> toDTOList(List<TaskGroup> taskGroupList) {
        return TaskGroup2DTOMapStruct.INSTANCE.toDTOList(taskGroupList);
    }

    @Override
    public List<TaskGroup> toDomainList(List<TaskGroupDTO> taskGroupDTOList) {
        return TaskGroup2DTOMapStruct.INSTANCE.toDomainList(taskGroupDTOList);
    }

    @Override
    public Page<TaskGroupDTO> toDTOPage(Page<TaskGroup> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), TaskGroup2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
