package io.kenxue.devops.application.project.task.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskRepository;
import io.kenxue.devops.domain.domain.project.Task;
import io.kenxue.devops.coreclient.dto.project.task.TaskDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Component
public class TaskDeleteCmdExe {
    @Resource
    private TaskRepository taskRepository;

    public Response execute(TaskDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Task task =taskRepository.getById(id);
            task.deleted(UserThreadContext.get());
            taskRepository.update(task);
        }
        return Response.success();
    }
}
