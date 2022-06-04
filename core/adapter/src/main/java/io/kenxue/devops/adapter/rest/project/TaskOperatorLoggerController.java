package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskOperatorLoggerAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@RestController
@Api(tags = "项目任务操作日志模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskoperatorlogger")
public class TaskOperatorLoggerController extends BasicController {
    @Resource
    private TaskOperatorLoggerAppService taskOperatorLoggerAppService;

    @PostMapping("/add")
    @Permissions("project:taskoperatorlogger:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid TaskOperatorLoggerAddCmd cmd) {
        return taskOperatorLoggerAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskoperatorlogger:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid TaskOperatorLoggerDeleteCmd cmd){
        return taskOperatorLoggerAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskoperatorlogger:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskOperatorLoggerDTO.class)
    })
    public Response page(@ModelAttribute @Valid TaskOperatorLoggerPageQry qry){
        return taskOperatorLoggerAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskoperatorlogger:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskOperatorLoggerDTO.class)
    })
    public Response list(@ModelAttribute @Valid TaskOperatorLoggerListQry qry){
        return taskOperatorLoggerAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskoperatorlogger:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskOperatorLoggerDTO.class)
    })
    public Response info(@ModelAttribute @Valid TaskOperatorLoggerGetQry qry){
        return taskOperatorLoggerAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskoperatorlogger:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody TaskOperatorLoggerUpdateCmd cmd){
        return taskOperatorLoggerAppService.update(cmd);
    }
}
