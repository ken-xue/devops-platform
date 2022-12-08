package io.kenxue.devops.application.project.taskfile.command;

import io.kenxue.devops.application.project.taskfile.assembler.TaskFile2DTOAssembler;
import io.kenxue.devops.domain.domain.project.TaskFile;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileUpdateCmd;
import io.kenxue.devops.domain.repository.project.TaskFileRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskFileUpdateCmdExe {

    @Resource
    private TaskFileRepository taskFileRepository;
    @Resource
    private TaskFile2DTOAssembler taskFile2DTOAssembler;

    public Response execute(TaskFileUpdateCmd cmd) {
        TaskFile taskFile = taskFile2DTOAssembler.toDomain(cmd.getTaskFileDTO());
        taskFileRepository.update(taskFile);
        return Response.success();
    }
}