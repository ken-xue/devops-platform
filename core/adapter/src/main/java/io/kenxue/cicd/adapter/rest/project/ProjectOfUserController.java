package io.kenxue.cicd.adapter.rest.project;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.project.ProjectOfUserAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.*;
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
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@RestController
@Api(tags = "项目关联用户模块",description = "包含新增/列表/删除")
@RequestMapping("project/projectofuser")
public class ProjectOfUserController extends BasicController {
    @Resource
    private ProjectOfUserAppService projectOfUserAppService;

    @PostMapping("/add")
    @Permissions("project:projectofuser:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ProjectOfUserAddCmd projectOfUserAddCmd) {
        return projectOfUserAppService.add(projectOfUserAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:projectofuser:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ProjectOfUserDeleteCmd projectOfUserDeleteCmd){
        return projectOfUserAppService.delete(projectOfUserDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("project:projectofuser:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ProjectOfUserPageQry projectOfUserPageQry){
        return projectOfUserAppService.page(projectOfUserPageQry);
    }

    @GetMapping("/list")
    @Permissions("project:projectofuser:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ProjectOfUserListQry projectOfUserListQry){
        return projectOfUserAppService.list(projectOfUserListQry);
    }

    @GetMapping("/info")
    @Permissions("project:projectofuser:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ProjectOfUserGetQry projectOfUserGetQry){
        return projectOfUserAppService.getById(projectOfUserGetQry);
    }

    @PutMapping("/update")
    @Permissions("project:projectofuser:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ProjectOfUserUpdateCmd projectOfUserUpdateCmd){
        return projectOfUserAppService.update(projectOfUserUpdateCmd);
    }
}
