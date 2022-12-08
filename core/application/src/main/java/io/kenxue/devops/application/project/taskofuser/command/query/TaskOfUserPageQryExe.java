package io.kenxue.devops.application.project.taskofuser.command.query;

import io.kenxue.devops.application.project.taskofuser.assembler.TaskOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserDTO;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.project.TaskOfUserRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOfUserPageQryExe {

    @Resource
    private TaskOfUserRepository taskOfUserRepository;
    @Resource
    private TaskOfUser2DTOAssembler taskOfUser2DTOAssembler;

    public PageResponse<TaskOfUserDTO> execute(TaskOfUserPageQry qry) {
        Page<TaskOfUser> page = taskOfUserRepository.page(qry);
        return PageResponse.of(taskOfUser2DTOAssembler.toDTOPage(page));
    }
}
