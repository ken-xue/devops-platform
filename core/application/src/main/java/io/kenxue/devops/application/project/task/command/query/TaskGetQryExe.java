package io.kenxue.devops.application.project.task.command.query;

import io.kenxue.devops.application.project.task.assembler.Task2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.task.TaskDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.task.TaskGetQry;
import io.kenxue.devops.domain.repository.project.TaskRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Component
public class TaskGetQryExe {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private Task2DTOAssembler task2DTOAssembler;

    public SingleResponse<TaskDTO> execute(TaskGetQry qry) {
        return SingleResponse.of(task2DTOAssembler.toDTO(taskRepository.getById(qry.getId())));
    }

}
