package io.kenxue.devops.application.project.task.command.query;

import io.kenxue.devops.application.project.task.assembler.Task2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.task.TaskDTO;
import io.kenxue.devops.coreclient.dto.project.task.TaskPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.project.TaskRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.project.Task;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Component
public class TaskPageQryExe {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private Task2DTOAssembler task2DTOAssembler;

    public PageResponse<TaskDTO> execute(TaskPageQry qry) {
        Page<Task> page = taskRepository.page(qry);
        return PageResponse.of(task2DTOAssembler.toDTOPage(page));
    }
}
