package io.kenxue.devops.application.project.task.service;

import io.kenxue.devops.coreclient.api.project.TaskAppService;
import io.kenxue.devops.coreclient.dto.project.task.*;
import io.kenxue.devops.application.project.task.command.TaskAddCmdExe;
import io.kenxue.devops.application.project.task.command.TaskDeleteCmdExe;
import io.kenxue.devops.application.project.task.command.TaskUpdateCmdExe;
import io.kenxue.devops.application.project.task.command.query.TaskGetQryExe;
import io.kenxue.devops.application.project.task.command.query.TaskListQryExe;
import io.kenxue.devops.application.project.task.command.query.TaskPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Service
public class TaskAppServiceImpl implements TaskAppService {
    @Resource
    private TaskAddCmdExe taskAddCmdExe;
    @Resource
    private TaskUpdateCmdExe taskUpdateCmdExe;
    @Resource
    private TaskGetQryExe taskGetQryExe;
    @Resource
    private TaskListQryExe taskListQryExe;
    @Resource
    private TaskPageQryExe taskPageQryExe;
    @Resource
    private TaskDeleteCmdExe taskDeleteCmdExe;

    public Response add(TaskAddCmd taskAddCmd) {
        return  taskAddCmdExe.execute(taskAddCmd);
    }

    public Response update(TaskUpdateCmd cmd) {
        return taskUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<TaskDTO> getById(TaskGetQry qry) {
        return taskGetQryExe.execute(qry);
    }

    public MultiResponse<TaskDTO> list(TaskListQry qry) {
        return taskListQryExe.execute(qry);
    }

    public Response delete(TaskDeleteCmd taskDeleteCmd) {
        return taskDeleteCmdExe.execute(taskDeleteCmd);
    }

    public PageResponse<TaskDTO> page(TaskPageQry taskPageQry) {
        return taskPageQryExe.execute(taskPageQry);
    }

}
