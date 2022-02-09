package io.kenxue.cicd.adapter.rest.application;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.application.ApplicationOfUserAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@RestController
@Api(tags = "应用关联用户模块",description = "包含新增/列表/删除")
@RequestMapping("application/applicationofuser")
public class ApplicationOfUserController extends BasicController {
    @Resource
    private ApplicationOfUserAppService applicationOfUserAppService;

    @PostMapping("/add")
    @Permissions("application:applicationofuser:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ApplicationOfUserAddCmd applicationOfUserAddCmd) {
        return applicationOfUserAppService.add(applicationOfUserAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationofuser:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ApplicationOfUserDeleteCmd applicationOfUserDeleteCmd){
        return applicationOfUserAppService.delete(applicationOfUserDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationofuser:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ApplicationOfUserPageQry applicationOfUserPageQry){
        return applicationOfUserAppService.page(applicationOfUserPageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationofuser:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ApplicationOfUserListQry applicationOfUserListQry){
        return applicationOfUserAppService.list(applicationOfUserListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationofuser:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ApplicationOfUserGetQry applicationOfUserGetQry){
        return applicationOfUserAppService.getById(applicationOfUserGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationofuser:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ApplicationOfUserUpdateCmd applicationOfUserUpdateCmd){
        return applicationOfUserAppService.update(applicationOfUserUpdateCmd);
    }
}
