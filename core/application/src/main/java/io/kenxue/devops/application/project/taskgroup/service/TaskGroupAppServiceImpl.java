package io.kenxue.devops.application.project.taskgroup.service;

import io.kenxue.devops.coreclient.api.project.TaskGroupAppService;
import io.kenxue.devops.coreclient.dto.project.taskgroup.*;
import io.kenxue.devops.application.project.taskgroup.command.TaskGroupAddCmdExe;
import io.kenxue.devops.application.project.taskgroup.command.TaskGroupDeleteCmdExe;
import io.kenxue.devops.application.project.taskgroup.command.TaskGroupUpdateCmdExe;
import io.kenxue.devops.application.project.taskgroup.command.query.TaskGroupGetQryExe;
import io.kenxue.devops.application.project.taskgroup.command.query.TaskGroupListQryExe;
import io.kenxue.devops.application.project.taskgroup.command.query.TaskGroupPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Service
public class TaskGroupAppServiceImpl implements TaskGroupAppService {
    @Resource
    private TaskGroupAddCmdExe taskGroupAddCmdExe;
    @Resource
    private TaskGroupUpdateCmdExe taskGroupUpdateCmdExe;
    @Resource
    private TaskGroupGetQryExe taskGroupGetQryExe;
    @Resource
    private TaskGroupListQryExe taskGroupListQryExe;
    @Resource
    private TaskGroupPageQryExe taskGroupPageQryExe;
    @Resource
    private TaskGroupDeleteCmdExe taskGroupDeleteCmdExe;

    public Response add(TaskGroupAddCmd taskGroupAddCmd) {
        return  taskGroupAddCmdExe.execute(taskGroupAddCmd);
    }

    public Response update(TaskGroupUpdateCmd cmd) {
        return taskGroupUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<TaskGroupDTO> getById(TaskGroupGetQry qry) {
        return taskGroupGetQryExe.execute(qry);
    }

    public MultiResponse<TaskGroupDTO> list(TaskGroupListQry qry) {
        return taskGroupListQryExe.execute(qry);
    }

    public Response delete(TaskGroupDeleteCmd taskGroupDeleteCmd) {
        return taskGroupDeleteCmdExe.execute(taskGroupDeleteCmd);
    }

    public PageResponse<TaskGroupDTO> page(TaskGroupPageQry taskGroupPageQry) {
        return taskGroupPageQryExe.execute(taskGroupPageQry);
    }

}
