package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskGroupAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskgroup.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@RestController
@Tag(name = "任务组模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskgroup")
public class TaskGroupController extends BasicController {
    @Resource
    private TaskGroupAppService taskGroupAppService;

    @PostMapping("/add")
    @Permissions("project:taskgroup:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid TaskGroupAddCmd cmd) {
        return taskGroupAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskgroup:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid TaskGroupDeleteCmd cmd){
        return taskGroupAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskgroup:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid TaskGroupPageQry qry){
        return taskGroupAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskgroup:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid TaskGroupListQry qry){
        return taskGroupAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskgroup:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid TaskGroupGetQry qry){
        return taskGroupAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskgroup:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody TaskGroupUpdateCmd cmd){
        return taskGroupAppService.update(cmd);
    }
}
