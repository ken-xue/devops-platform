package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.TaskWorkingHoursAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.*;
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
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@RestController
@Api(tags = "工时模块",description = "包含新增/列表/删除")
@RequestMapping("project/taskworkinghours")
public class TaskWorkingHoursController extends BasicController {
    @Resource
    private TaskWorkingHoursAppService taskWorkingHoursAppService;

    @PostMapping("/add")
    @Permissions("project:taskworkinghours:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid TaskWorkingHoursAddCmd cmd) {
        return taskWorkingHoursAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:taskworkinghours:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid TaskWorkingHoursDeleteCmd cmd){
        return taskWorkingHoursAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("project:taskworkinghours:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskWorkingHoursDTO.class)
    })
    public Response page(@ModelAttribute @Valid TaskWorkingHoursPageQry qry){
        return taskWorkingHoursAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("project:taskworkinghours:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskWorkingHoursDTO.class)
    })
    public Response list(@ModelAttribute @Valid TaskWorkingHoursListQry qry){
        return taskWorkingHoursAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("project:taskworkinghours:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = TaskWorkingHoursDTO.class)
    })
    public Response info(@ModelAttribute @Valid TaskWorkingHoursGetQry qry){
        return taskWorkingHoursAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("project:taskworkinghours:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody TaskWorkingHoursUpdateCmd cmd){
        return taskWorkingHoursAppService.update(cmd);
    }
}
