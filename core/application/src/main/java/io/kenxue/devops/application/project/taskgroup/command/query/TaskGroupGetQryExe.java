package io.kenxue.devops.application.project.taskgroup.command.query;

import io.kenxue.devops.application.project.taskgroup.assembler.TaskGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupGetQry;
import io.kenxue.devops.domain.repository.project.TaskGroupRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Component
public class TaskGroupGetQryExe {

    @Resource
    private TaskGroupRepository taskGroupRepository;
    @Resource
    private TaskGroup2DTOAssembler taskGroup2DTOAssembler;

    public SingleResponse<TaskGroupDTO> execute(TaskGroupGetQry qry) {
        return SingleResponse.of(taskGroup2DTOAssembler.toDTO(taskGroupRepository.getById(qry.getId())));
    }

}
