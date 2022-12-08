package io.kenxue.devops.adapter.rest.application;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.application.ApplicationOfUserAppService;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.*;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@RestController
@Tag(name = "应用关联用户模块",description = "包含新增/列表/删除")
@RequestMapping("application/applicationofuser")
public class ApplicationOfUserController extends BasicController {
    @Resource
    private ApplicationOfUserAppService applicationOfUserAppService;

    @PostMapping("/add")
    @Permissions("application:applicationofuser:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ApplicationOfUserAddCmd applicationOfUserAddCmd) {
        return applicationOfUserAppService.add(applicationOfUserAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationofuser:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ApplicationOfUserDeleteCmd applicationOfUserDeleteCmd){
        return applicationOfUserAppService.delete(applicationOfUserDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationofuser:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ApplicationOfUserPageQry applicationOfUserPageQry){
        return applicationOfUserAppService.page(applicationOfUserPageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationofuser:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ApplicationOfUserListQry applicationOfUserListQry){
        return applicationOfUserAppService.list(applicationOfUserListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationofuser:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ApplicationOfUserGetQry applicationOfUserGetQry){
        return applicationOfUserAppService.getById(applicationOfUserGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationofuser:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ApplicationOfUserUpdateCmd applicationOfUserUpdateCmd){
        return applicationOfUserAppService.update(applicationOfUserUpdateCmd);
    }
}
