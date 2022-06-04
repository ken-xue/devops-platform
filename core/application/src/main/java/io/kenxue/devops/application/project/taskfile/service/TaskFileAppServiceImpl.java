package io.kenxue.devops.application.project.taskfile.service;

import io.kenxue.devops.coreclient.api.project.TaskFileAppService;
import io.kenxue.devops.coreclient.dto.project.taskfile.*;
import io.kenxue.devops.application.project.taskfile.command.TaskFileAddCmdExe;
import io.kenxue.devops.application.project.taskfile.command.TaskFileDeleteCmdExe;
import io.kenxue.devops.application.project.taskfile.command.TaskFileUpdateCmdExe;
import io.kenxue.devops.application.project.taskfile.command.query.TaskFileGetQryExe;
import io.kenxue.devops.application.project.taskfile.command.query.TaskFileListQryExe;
import io.kenxue.devops.application.project.taskfile.command.query.TaskFilePageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Service
public class TaskFileAppServiceImpl implements TaskFileAppService {
    @Resource
    private TaskFileAddCmdExe taskFileAddCmdExe;
    @Resource
    private TaskFileUpdateCmdExe taskFileUpdateCmdExe;
    @Resource
    private TaskFileGetQryExe taskFileGetQryExe;
    @Resource
    private TaskFileListQryExe taskFileListQryExe;
    @Resource
    private TaskFilePageQryExe taskFilePageQryExe;
    @Resource
    private TaskFileDeleteCmdExe taskFileDeleteCmdExe;

    public Response add(TaskFileAddCmd taskFileAddCmd) {
        return  taskFileAddCmdExe.execute(taskFileAddCmd);
    }

    public Response update(TaskFileUpdateCmd cmd) {
        return taskFileUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<TaskFileDTO> getById(TaskFileGetQry qry) {
        return taskFileGetQryExe.execute(qry);
    }

    public MultiResponse<TaskFileDTO> list(TaskFileListQry qry) {
        return taskFileListQryExe.execute(qry);
    }

    public Response delete(TaskFileDeleteCmd taskFileDeleteCmd) {
        return taskFileDeleteCmdExe.execute(taskFileDeleteCmd);
    }

    public PageResponse<TaskFileDTO> page(TaskFilePageQry taskFilePageQry) {
        return taskFilePageQryExe.execute(taskFilePageQry);
    }

}
