package io.kenxue.devops.application.project.taskfile.command.query;

import io.kenxue.devops.application.project.taskfile.assembler.TaskFile2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileDTO;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFilePageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.project.TaskFileRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.project.TaskFile;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskFilePageQryExe {

    @Resource
    private TaskFileRepository taskFileRepository;
    @Resource
    private TaskFile2DTOAssembler taskFile2DTOAssembler;

    public PageResponse<TaskFileDTO> execute(TaskFilePageQry qry) {
        Page<TaskFile> page = taskFileRepository.page(qry);
        return PageResponse.of(taskFile2DTOAssembler.toDTOPage(page));
    }
}
