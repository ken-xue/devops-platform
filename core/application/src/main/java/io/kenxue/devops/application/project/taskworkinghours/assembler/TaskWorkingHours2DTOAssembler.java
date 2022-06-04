package io.kenxue.devops.application.project.taskworkinghours.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursDTO;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskWorkingHours2DTOAssembler implements Assembler<TaskWorkingHoursDTO, TaskWorkingHours> {

    @Override
    public TaskWorkingHoursDTO toDTO(TaskWorkingHours taskWorkingHours) {
        return TaskWorkingHours2DTOMapStruct.INSTANCE.toDTO(taskWorkingHours);
    }

    @Override
    public TaskWorkingHours toDomain(TaskWorkingHoursDTO taskWorkingHoursDTO) {
        return TaskWorkingHours2DTOMapStruct.INSTANCE.toDomain(taskWorkingHoursDTO);
    }

    @Override
    public List<TaskWorkingHoursDTO> toDTOList(List<TaskWorkingHours> taskWorkingHoursList) {
        return TaskWorkingHours2DTOMapStruct.INSTANCE.toDTOList(taskWorkingHoursList);
    }

    @Override
    public List<TaskWorkingHours> toDomainList(List<TaskWorkingHoursDTO> taskWorkingHoursDTOList) {
        return TaskWorkingHours2DTOMapStruct.INSTANCE.toDomainList(taskWorkingHoursDTOList);
    }

    @Override
    public Page<TaskWorkingHoursDTO> toDTOPage(Page<TaskWorkingHours> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), TaskWorkingHours2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
