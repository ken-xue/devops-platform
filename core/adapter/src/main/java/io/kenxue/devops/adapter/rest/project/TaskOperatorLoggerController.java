package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskOperatorLoggerAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@RestController
@Tag(name = "项目任务操作日志模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskoperatorlogger")
public class TaskOperatorLoggerController extends BasicController {
    @Resource
    private TaskOperatorLoggerAppService taskOperatorLoggerAppService;

    @PostMapping("/add")
    @Permissions("project:taskoperatorlogger:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid TaskOperatorLoggerAddCmd cmd) {
        return taskOperatorLoggerAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskoperatorlogger:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid TaskOperatorLoggerDeleteCmd cmd){
        return taskOperatorLoggerAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskoperatorlogger:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid TaskOperatorLoggerPageQry qry){
        return taskOperatorLoggerAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskoperatorlogger:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid TaskOperatorLoggerListQry qry){
        return taskOperatorLoggerAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskoperatorlogger:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid TaskOperatorLoggerGetQry qry){
        return taskOperatorLoggerAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskoperatorlogger:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody TaskOperatorLoggerUpdateCmd cmd){
        return taskOperatorLoggerAppService.update(cmd);
    }
}
