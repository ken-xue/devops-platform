package io.kenxue.devops.application.project.taskworkinghours.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskWorkingHoursRepository;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskWorkingHoursDeleteCmdExe {
    @Resource
    private TaskWorkingHoursRepository taskWorkingHoursRepository;

    public Response execute(TaskWorkingHoursDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            TaskWorkingHours taskWorkingHours =taskWorkingHoursRepository.getById(id);
            taskWorkingHours.deleted(UserThreadContext.get());
            taskWorkingHoursRepository.update(taskWorkingHours);
        }
        return Response.success();
    }
}
