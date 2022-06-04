package io.kenxue.devops.application.project.taskofuser.command.query;

import io.kenxue.devops.application.project.taskofuser.assembler.TaskOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserGetQry;
import io.kenxue.devops.domain.repository.project.TaskOfUserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOfUserGetQryExe {

    @Resource
    private TaskOfUserRepository taskOfUserRepository;
    @Resource
    private TaskOfUser2DTOAssembler taskOfUser2DTOAssembler;

    public SingleResponse<TaskOfUserDTO> execute(TaskOfUserGetQry qry) {
        return SingleResponse.of(taskOfUser2DTOAssembler.toDTO(taskOfUserRepository.getById(qry.getId())));
    }

}
