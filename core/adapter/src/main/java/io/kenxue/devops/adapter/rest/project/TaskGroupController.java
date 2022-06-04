package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskGroupAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskgroup.*;
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
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@RestController
@Api(tags = "任务组模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskgroup")
public class TaskGroupController extends BasicController {
    @Resource
    private TaskGroupAppService taskGroupAppService;

    @PostMapping("/add")
    @Permissions("project:taskgroup:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid TaskGroupAddCmd cmd) {
        return taskGroupAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskgroup:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid TaskGroupDeleteCmd cmd){
        return taskGroupAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskgroup:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskGroupDTO.class)
    })
    public Response page(@ModelAttribute @Valid TaskGroupPageQry qry){
        return taskGroupAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskgroup:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskGroupDTO.class)
    })
    public Response list(@ModelAttribute @Valid TaskGroupListQry qry){
        return taskGroupAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskgroup:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskGroupDTO.class)
    })
    public Response info(@ModelAttribute @Valid TaskGroupGetQry qry){
        return taskGroupAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskgroup:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody TaskGroupUpdateCmd cmd){
        return taskGroupAppService.update(cmd);
    }
}
