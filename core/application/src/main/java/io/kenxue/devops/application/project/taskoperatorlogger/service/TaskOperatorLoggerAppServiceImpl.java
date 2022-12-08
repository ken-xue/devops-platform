package io.kenxue.devops.application.project.taskoperatorlogger.service;

import io.kenxue.devops.coreclient.api.project.TaskOperatorLoggerAppService;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.*;
import io.kenxue.devops.application.project.taskoperatorlogger.command.TaskOperatorLoggerAddCmdExe;
import io.kenxue.devops.application.project.taskoperatorlogger.command.TaskOperatorLoggerDeleteCmdExe;
import io.kenxue.devops.application.project.taskoperatorlogger.command.TaskOperatorLoggerUpdateCmdExe;
import io.kenxue.devops.application.project.taskoperatorlogger.command.query.TaskOperatorLoggerGetQryExe;
import io.kenxue.devops.application.project.taskoperatorlogger.command.query.TaskOperatorLoggerListQryExe;
import io.kenxue.devops.application.project.taskoperatorlogger.command.query.TaskOperatorLoggerPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Service
public class TaskOperatorLoggerAppServiceImpl implements TaskOperatorLoggerAppService {
    @Resource
    private TaskOperatorLoggerAddCmdExe taskOperatorLoggerAddCmdExe;
    @Resource
    private TaskOperatorLoggerUpdateCmdExe taskOperatorLoggerUpdateCmdExe;
    @Resource
    private TaskOperatorLoggerGetQryExe taskOperatorLoggerGetQryExe;
    @Resource
    private TaskOperatorLoggerListQryExe taskOperatorLoggerListQryExe;
    @Resource
    private TaskOperatorLoggerPageQryExe taskOperatorLoggerPageQryExe;
    @Resource
    private TaskOperatorLoggerDeleteCmdExe taskOperatorLoggerDeleteCmdExe;

    public Response add(TaskOperatorLoggerAddCmd taskOperatorLoggerAddCmd) {
        return  taskOperatorLoggerAddCmdExe.execute(taskOperatorLoggerAddCmd);
    }

    public Response update(TaskOperatorLoggerUpdateCmd cmd) {
        return taskOperatorLoggerUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<TaskOperatorLoggerDTO> getById(TaskOperatorLoggerGetQry qry) {
        return taskOperatorLoggerGetQryExe.execute(qry);
    }

    public MultiResponse<TaskOperatorLoggerDTO> list(TaskOperatorLoggerListQry qry) {
        return taskOperatorLoggerListQryExe.execute(qry);
    }

    public Response delete(TaskOperatorLoggerDeleteCmd taskOperatorLoggerDeleteCmd) {
        return taskOperatorLoggerDeleteCmdExe.execute(taskOperatorLoggerDeleteCmd);
    }

    public PageResponse<TaskOperatorLoggerDTO> page(TaskOperatorLoggerPageQry taskOperatorLoggerPageQry) {
        return taskOperatorLoggerPageQryExe.execute(taskOperatorLoggerPageQry);
    }

}
