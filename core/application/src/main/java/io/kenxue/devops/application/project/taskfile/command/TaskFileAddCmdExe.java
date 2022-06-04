package io.kenxue.devops.application.project.taskfile.command;

import io.kenxue.devops.application.project.taskfile.assembler.TaskFile2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskFileRepository;
import io.kenxue.devops.domain.domain.project.TaskFile;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskFileAddCmdExe {

    @Resource
    private TaskFileRepository taskFileRepository;
    @Resource
    private TaskFile2DTOAssembler taskFile2DTOAssembler;

    public Response execute(TaskFileAddCmd cmd) {
        TaskFile taskFile = taskFile2DTOAssembler.toDomain(cmd.getTaskFileDTO());
        taskFile.create();
        taskFileRepository.create(taskFile);
        return Response.success();
    }
}
