package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskOfUserAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskofuser.*;
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
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@RestController
@Api(tags = "任务关联用户模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskofuser")
public class TaskOfUserController extends BasicController {
    @Resource
    private TaskOfUserAppService taskOfUserAppService;

    @PostMapping("/add")
    @Permissions("project:taskofuser:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid TaskOfUserAddCmd cmd) {
        return taskOfUserAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskofuser:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid TaskOfUserDeleteCmd cmd){
        return taskOfUserAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskofuser:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskOfUserDTO.class)
    })
    public Response page(@ModelAttribute @Valid TaskOfUserPageQry qry){
        return taskOfUserAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskofuser:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskOfUserDTO.class)
    })
    public Response list(@ModelAttribute @Valid TaskOfUserListQry qry){
        return taskOfUserAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskofuser:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskOfUserDTO.class)
    })
    public Response info(@ModelAttribute @Valid TaskOfUserGetQry qry){
        return taskOfUserAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskofuser:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody TaskOfUserUpdateCmd cmd){
        return taskOfUserAppService.update(cmd);
    }
}
