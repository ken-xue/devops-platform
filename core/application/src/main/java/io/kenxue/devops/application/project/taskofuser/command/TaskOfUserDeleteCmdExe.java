package io.kenxue.devops.application.project.taskofuser.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskOfUserRepository;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOfUserDeleteCmdExe {
    @Resource
    private TaskOfUserRepository taskOfUserRepository;

    public Response execute(TaskOfUserDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            TaskOfUser taskOfUser =taskOfUserRepository.getById(id);
            taskOfUser.deleted(UserThreadContext.get());
            taskOfUserRepository.update(taskOfUser);
        }
        return Response.success();
    }
}
