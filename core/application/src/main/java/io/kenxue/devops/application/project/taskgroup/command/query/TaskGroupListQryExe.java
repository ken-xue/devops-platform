package io.kenxue.devops.application.project.taskgroup.command.query;

import io.kenxue.devops.application.project.taskgroup.assembler.TaskGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupDTO;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupListQry;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import io.kenxue.devops.domain.repository.project.TaskGroupRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Component
public class TaskGroupListQryExe {
    @Resource
    private TaskGroupRepository taskGroupRepository;
    @Resource
    private TaskGroup2DTOAssembler taskGroup2DTOAssembler;

    public MultiResponse<TaskGroupDTO> execute(TaskGroupListQry qry) {
        List<TaskGroup> taskGroupDTOList = taskGroupRepository.list(qry);
        List<TaskGroupDTO> taskGroupDTOS = taskGroup2DTOAssembler.toDTOList(taskGroupDTOList);
        return MultiResponse.of(taskGroupDTOS);
    }
}
