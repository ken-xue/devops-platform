package io.kenxue.devops.application.project.taskofuser.command;

import io.kenxue.devops.application.project.taskofuser.assembler.TaskOfUser2DTOAssembler;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserUpdateCmd;
import io.kenxue.devops.domain.repository.project.TaskOfUserRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOfUserUpdateCmdExe {

    @Resource
    private TaskOfUserRepository taskOfUserRepository;
    @Resource
    private TaskOfUser2DTOAssembler taskOfUser2DTOAssembler;

    public Response execute(TaskOfUserUpdateCmd cmd) {
        TaskOfUser taskOfUser = taskOfUser2DTOAssembler.toDomain(cmd.getTaskOfUserDTO());
        taskOfUserRepository.update(taskOfUser);
        return Response.success();
    }
}