package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskFileAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskfile.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@RestController
@Tag(name = "任务文件模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskfile")
public class TaskFileController extends BasicController {
    @Resource
    private TaskFileAppService taskFileAppService;

    @PostMapping("/add")
    @Permissions("project:taskfile:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid TaskFileAddCmd cmd) {
        return taskFileAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskfile:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid TaskFileDeleteCmd cmd){
        return taskFileAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskfile:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid TaskFilePageQry qry){
        return taskFileAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskfile:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid TaskFileListQry qry){
        return taskFileAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskfile:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid TaskFileGetQry qry){
        return taskFileAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskfile:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody TaskFileUpdateCmd cmd){
        return taskFileAppService.update(cmd);
    }
}
