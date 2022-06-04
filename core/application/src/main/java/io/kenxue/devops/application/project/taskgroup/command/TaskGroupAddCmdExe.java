package io.kenxue.devops.application.project.taskgroup.command;

import io.kenxue.devops.application.project.taskgroup.assembler.TaskGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskGroupRepository;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Component
public class TaskGroupAddCmdExe {

    @Resource
    private TaskGroupRepository taskGroupRepository;
    @Resource
    private TaskGroup2DTOAssembler taskGroup2DTOAssembler;

    public Response execute(TaskGroupAddCmd cmd) {
        TaskGroup taskGroup = taskGroup2DTOAssembler.toDomain(cmd.getTaskGroupDTO());
        taskGroup.create();
        taskGroupRepository.create(taskGroup);
        return Response.success();
    }
}
