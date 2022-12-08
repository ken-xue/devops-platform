package io.kenxue.devops.application.project.taskofuser.command.query;

import io.kenxue.devops.application.project.taskofuser.assembler.TaskOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserDTO;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserListQry;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import io.kenxue.devops.domain.repository.project.TaskOfUserRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOfUserListQryExe {
    @Resource
    private TaskOfUserRepository taskOfUserRepository;
    @Resource
    private TaskOfUser2DTOAssembler taskOfUser2DTOAssembler;

    public MultiResponse<TaskOfUserDTO> execute(TaskOfUserListQry qry) {
        List<TaskOfUser> taskOfUserDTOList = taskOfUserRepository.list(qry);
        return MultiResponse.of(taskOfUser2DTOAssembler.toDTOList(taskOfUserDTOList));
    }
}
