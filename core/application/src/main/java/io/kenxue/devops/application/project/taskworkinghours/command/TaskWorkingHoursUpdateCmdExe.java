package io.kenxue.devops.application.project.taskworkinghours.command;

import io.kenxue.devops.application.project.taskworkinghours.assembler.TaskWorkingHours2DTOAssembler;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursUpdateCmd;
import io.kenxue.devops.domain.repository.project.TaskWorkingHoursRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskWorkingHoursUpdateCmdExe {

    @Resource
    private TaskWorkingHoursRepository taskWorkingHoursRepository;
    @Resource
    private TaskWorkingHours2DTOAssembler taskWorkingHours2DTOAssembler;

    public Response execute(TaskWorkingHoursUpdateCmd cmd) {
        TaskWorkingHours taskWorkingHours = taskWorkingHours2DTOAssembler.toDomain(cmd.getTaskWorkingHoursDTO());
        taskWorkingHoursRepository.update(taskWorkingHours);
        return Response.success();
    }
}