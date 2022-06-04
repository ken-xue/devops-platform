package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskFileAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskfile.*;
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
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@RestController
@Api(tags = "任务文件模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskfile")
public class TaskFileController extends BasicController {
    @Resource
    private TaskFileAppService taskFileAppService;

    @PostMapping("/add")
    @Permissions("project:taskfile:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid TaskFileAddCmd cmd) {
        return taskFileAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskfile:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid TaskFileDeleteCmd cmd){
        return taskFileAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskfile:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskFileDTO.class)
    })
    public Response page(@ModelAttribute @Valid TaskFilePageQry qry){
        return taskFileAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskfile:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskFileDTO.class)
    })
    public Response list(@ModelAttribute @Valid TaskFileListQry qry){
        return taskFileAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskfile:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskFileDTO.class)
    })
    public Response info(@ModelAttribute @Valid TaskFileGetQry qry){
        return taskFileAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskfile:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody TaskFileUpdateCmd cmd){
        return taskFileAppService.update(cmd);
    }
}
