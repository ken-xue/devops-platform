package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskWorkingHoursAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@RestController
@Tag(name = "工时模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskworkinghours")
public class TaskWorkingHoursController extends BasicController {
    @Resource
    private TaskWorkingHoursAppService taskWorkingHoursAppService;

    @PostMapping("/add")
    @Permissions("project:taskworkinghours:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid TaskWorkingHoursAddCmd cmd) {
        return taskWorkingHoursAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskworkinghours:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid TaskWorkingHoursDeleteCmd cmd){
        return taskWorkingHoursAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskworkinghours:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid TaskWorkingHoursPageQry qry){
        return taskWorkingHoursAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskworkinghours:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid TaskWorkingHoursListQry qry){
        return taskWorkingHoursAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskworkinghours:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid TaskWorkingHoursGetQry qry){
        return taskWorkingHoursAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskworkinghours:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody TaskWorkingHoursUpdateCmd cmd){
        return taskWorkingHoursAppService.update(cmd);
    }
}
