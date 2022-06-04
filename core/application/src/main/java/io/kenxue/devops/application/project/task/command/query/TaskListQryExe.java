package io.kenxue.devops.application.project.task.command.query;

import io.kenxue.devops.application.project.task.assembler.Task2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.project.task.TaskDTO;
import io.kenxue.devops.coreclient.dto.project.task.TaskListQry;
import io.kenxue.devops.domain.domain.project.Task;
import io.kenxue.devops.domain.repository.project.TaskRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Component
public class TaskListQryExe {
    @Resource
    private TaskRepository taskRepository;
    @Resource
    private Task2DTOAssembler task2DTOAssembler;

    public MultiResponse<TaskDTO> execute(TaskListQry qry) {
        List<Task> taskDTOList = taskRepository.list(qry);
        return MultiResponse.of(task2DTOAssembler.toDTOList(taskDTOList));
    }
}
