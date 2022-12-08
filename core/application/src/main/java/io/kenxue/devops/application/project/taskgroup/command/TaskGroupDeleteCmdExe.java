package io.kenxue.devops.application.project.taskgroup.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskGroupRepository;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Component
public class TaskGroupDeleteCmdExe {
    @Resource
    private TaskGroupRepository taskGroupRepository;

    public Response execute(TaskGroupDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            TaskGroup taskGroup =taskGroupRepository.getById(id);
            taskGroup.deleted(UserThreadContext.get());
            taskGroupRepository.update(taskGroup);
        }
        return Response.success();
    }
}
