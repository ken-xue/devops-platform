package io.kenxue.devops.application.project.taskofuser.service;

import io.kenxue.devops.coreclient.api.project.TaskOfUserAppService;
import io.kenxue.devops.coreclient.dto.project.taskofuser.*;
import io.kenxue.devops.application.project.taskofuser.command.TaskOfUserAddCmdExe;
import io.kenxue.devops.application.project.taskofuser.command.TaskOfUserDeleteCmdExe;
import io.kenxue.devops.application.project.taskofuser.command.TaskOfUserUpdateCmdExe;
import io.kenxue.devops.application.project.taskofuser.command.query.TaskOfUserGetQryExe;
import io.kenxue.devops.application.project.taskofuser.command.query.TaskOfUserListQryExe;
import io.kenxue.devops.application.project.taskofuser.command.query.TaskOfUserPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Service
public class TaskOfUserAppServiceImpl implements TaskOfUserAppService {
    @Resource
    private TaskOfUserAddCmdExe taskOfUserAddCmdExe;
    @Resource
    private TaskOfUserUpdateCmdExe taskOfUserUpdateCmdExe;
    @Resource
    private TaskOfUserGetQryExe taskOfUserGetQryExe;
    @Resource
    private TaskOfUserListQryExe taskOfUserListQryExe;
    @Resource
    private TaskOfUserPageQryExe taskOfUserPageQryExe;
    @Resource
    private TaskOfUserDeleteCmdExe taskOfUserDeleteCmdExe;

    public Response add(TaskOfUserAddCmd taskOfUserAddCmd) {
        return  taskOfUserAddCmdExe.execute(taskOfUserAddCmd);
    }

    public Response update(TaskOfUserUpdateCmd cmd) {
        return taskOfUserUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<TaskOfUserDTO> getById(TaskOfUserGetQry qry) {
        return taskOfUserGetQryExe.execute(qry);
    }

    public MultiResponse<TaskOfUserDTO> list(TaskOfUserListQry qry) {
        return taskOfUserListQryExe.execute(qry);
    }

    public Response delete(TaskOfUserDeleteCmd taskOfUserDeleteCmd) {
        return taskOfUserDeleteCmdExe.execute(taskOfUserDeleteCmd);
    }

    public PageResponse<TaskOfUserDTO> page(TaskOfUserPageQry taskOfUserPageQry) {
        return taskOfUserPageQryExe.execute(taskOfUserPageQry);
    }

}
