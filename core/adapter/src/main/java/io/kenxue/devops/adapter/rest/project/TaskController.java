package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.task.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@RestController
@Tag(name = "项目任务模块",description = "包含新增/列表/删除")
@RequestMapping("project/task")
public class TaskController extends BasicController {
    @Resource
    private TaskAppService taskAppService;

    @PostMapping("/add")
    @Permissions("project:task:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid TaskAddCmd cmd) {
        return taskAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:task:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid TaskDeleteCmd cmd){
        return taskAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:task:page")
    @Operation(summary = "列表",method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK")
    })
    public Response page(@ModelAttribute @Valid TaskPageQry qry){
        return taskAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:task:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid TaskListQry qry){
        return taskAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:task:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid TaskGetQry qry){
        return taskAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:task:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody TaskUpdateCmd cmd){
        return taskAppService.update(cmd);
    }
}
