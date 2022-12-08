package io.kenxue.devops.application.project.task.command;

import io.kenxue.devops.application.project.task.assembler.Task2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskRepository;
import io.kenxue.devops.domain.domain.project.Task;
import io.kenxue.devops.coreclient.dto.project.task.TaskAddCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Component
public class TaskAddCmdExe {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private Task2DTOAssembler task2DTOAssembler;

    public Response execute(TaskAddCmd cmd) {
        Task task = task2DTOAssembler.toDomain(cmd.getTaskDTO());
        task.create();
        taskRepository.create(task);
        return Response.success();
    }
}
