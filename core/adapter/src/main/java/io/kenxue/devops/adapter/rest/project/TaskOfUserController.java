package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskOfUserAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskofuser.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@RestController
@Tag(name = "任务关联用户模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskofuser")
public class TaskOfUserController extends BasicController {
    @Resource
    private TaskOfUserAppService taskOfUserAppService;

    @PostMapping("/add")
    @Permissions("project:taskofuser:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid TaskOfUserAddCmd cmd) {
        return taskOfUserAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskofuser:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid TaskOfUserDeleteCmd cmd){
        return taskOfUserAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskofuser:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid TaskOfUserPageQry qry){
        return taskOfUserAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskofuser:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid TaskOfUserListQry qry){
        return taskOfUserAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskofuser:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid TaskOfUserGetQry qry){
        return taskOfUserAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskofuser:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody TaskOfUserUpdateCmd cmd){
        return taskOfUserAppService.update(cmd);
    }
}
