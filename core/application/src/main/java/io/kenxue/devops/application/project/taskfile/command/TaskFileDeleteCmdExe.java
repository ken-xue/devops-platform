package io.kenxue.devops.application.project.taskfile.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskFileRepository;
import io.kenxue.devops.domain.domain.project.TaskFile;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskFileDeleteCmdExe {
    @Resource
    private TaskFileRepository taskFileRepository;

    public Response execute(TaskFileDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            TaskFile taskFile =taskFileRepository.getById(id);
            taskFile.deleted(UserThreadContext.get());
            taskFileRepository.update(taskFile);
        }
        return Response.success();
    }
}
