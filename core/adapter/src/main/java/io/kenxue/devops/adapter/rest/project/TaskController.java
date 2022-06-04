package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.task.*;
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
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@RestController
@Api(tags = "项目任务模块",description = "包含新增/列表/删除")
@RequestMapping("project/task")
public class TaskController extends BasicController {
    @Resource
    private TaskAppService taskAppService;

    @PostMapping("/add")
    @Permissions("project:task:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid TaskAddCmd cmd) {
        return taskAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:task:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid TaskDeleteCmd cmd){
        return taskAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:task:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskDTO.class)
    })
    public Response page(@ModelAttribute @Valid TaskPageQry qry){
        return taskAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:task:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskDTO.class)
    })
    public Response list(@ModelAttribute @Valid TaskListQry qry){
        return taskAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:task:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskDTO.class)
    })
    public Response info(@ModelAttribute @Valid TaskGetQry qry){
        return taskAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:task:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody TaskUpdateCmd cmd){
        return taskAppService.update(cmd);
    }
}
