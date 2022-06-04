package io.kenxue.devops.application.project.taskgroup.command.query;

import io.kenxue.devops.application.project.taskgroup.assembler.TaskGroup2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupDTO;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.project.TaskGroupRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Component
public class TaskGroupPageQryExe {

    @Resource
    private TaskGroupRepository taskGroupRepository;
    @Resource
    private TaskGroup2DTOAssembler taskGroup2DTOAssembler;

    public PageResponse<TaskGroupDTO> execute(TaskGroupPageQry qry) {
        Page<TaskGroup> page = taskGroupRepository.page(qry);
        return PageResponse.of(taskGroup2DTOAssembler.toDTOPage(page));
    }
}
