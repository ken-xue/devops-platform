package io.kenxue.devops.application.project.taskworkinghours.service;

import io.kenxue.devops.coreclient.api.project.TaskWorkingHoursAppService;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.*;
import io.kenxue.devops.application.project.taskworkinghours.command.TaskWorkingHoursAddCmdExe;
import io.kenxue.devops.application.project.taskworkinghours.command.TaskWorkingHoursDeleteCmdExe;
import io.kenxue.devops.application.project.taskworkinghours.command.TaskWorkingHoursUpdateCmdExe;
import io.kenxue.devops.application.project.taskworkinghours.command.query.TaskWorkingHoursGetQryExe;
import io.kenxue.devops.application.project.taskworkinghours.command.query.TaskWorkingHoursListQryExe;
import io.kenxue.devops.application.project.taskworkinghours.command.query.TaskWorkingHoursPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Service
public class TaskWorkingHoursAppServiceImpl implements TaskWorkingHoursAppService {
    @Resource
    private TaskWorkingHoursAddCmdExe taskWorkingHoursAddCmdExe;
    @Resource
    private TaskWorkingHoursUpdateCmdExe taskWorkingHoursUpdateCmdExe;
    @Resource
    private TaskWorkingHoursGetQryExe taskWorkingHoursGetQryExe;
    @Resource
    private TaskWorkingHoursListQryExe taskWorkingHoursListQryExe;
    @Resource
    private TaskWorkingHoursPageQryExe taskWorkingHoursPageQryExe;
    @Resource
    private TaskWorkingHoursDeleteCmdExe taskWorkingHoursDeleteCmdExe;

    public Response add(TaskWorkingHoursAddCmd taskWorkingHoursAddCmd) {
        return  taskWorkingHoursAddCmdExe.execute(taskWorkingHoursAddCmd);
    }

    public Response update(TaskWorkingHoursUpdateCmd cmd) {
        return taskWorkingHoursUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<TaskWorkingHoursDTO> getById(TaskWorkingHoursGetQry qry) {
        return taskWorkingHoursGetQryExe.execute(qry);
    }

    public MultiResponse<TaskWorkingHoursDTO> list(TaskWorkingHoursListQry qry) {
        return taskWorkingHoursListQryExe.execute(qry);
    }

    public Response delete(TaskWorkingHoursDeleteCmd taskWorkingHoursDeleteCmd) {
        return taskWorkingHoursDeleteCmdExe.execute(taskWorkingHoursDeleteCmd);
    }

    public PageResponse<TaskWorkingHoursDTO> page(TaskWorkingHoursPageQry taskWorkingHoursPageQry) {
        return taskWorkingHoursPageQryExe.execute(taskWorkingHoursPageQry);
    }

}
