package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.ProjectOfUserAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.projectofuser.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@RestController
@Tag(name = "项目关联用户模块",description = "包含新增/列表/删除")
@RequestMapping("project/projectofuser")
public class ProjectOfUserController extends BasicController {
    @Resource
    private ProjectOfUserAppService projectOfUserAppService;

    @PostMapping("/add")
    @Permissions("project:projectofuser:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ProjectOfUserAddCmd projectOfUserAddCmd) {
        return projectOfUserAppService.add(projectOfUserAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:projectofuser:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ProjectOfUserDeleteCmd projectOfUserDeleteCmd){
        return projectOfUserAppService.delete(projectOfUserDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("project:projectofuser:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ProjectOfUserPageQry projectOfUserPageQry){
        return projectOfUserAppService.page(projectOfUserPageQry);
    }

    @GetMapping("/list")
    @Permissions("project:projectofuser:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ProjectOfUserListQry projectOfUserListQry){
        return projectOfUserAppService.list(projectOfUserListQry);
    }

    @GetMapping("/info")
    @Permissions("project:projectofuser:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ProjectOfUserGetQry projectOfUserGetQry){
        return projectOfUserAppService.getById(projectOfUserGetQry);
    }

    @PutMapping("/update")
    @Permissions("project:projectofuser:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ProjectOfUserUpdateCmd projectOfUserUpdateCmd){
        return projectOfUserAppService.update(projectOfUserUpdateCmd);
    }
}
