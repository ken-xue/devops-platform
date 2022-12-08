package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.ProjectInfoAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.projectinfo.*;
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
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@RestController
@Tag(name = "项目模块",description = "包含新增/列表/删除")
@RequestMapping("project")
public class ProjectInfoController extends BasicController {
    @Resource
    private ProjectInfoAppService projectInfoAppService;

    @PostMapping("/add")
    @Permissions("project:projectinfo:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ProjectInfoAddCmd projectInfoAddCmd) {
        return projectInfoAppService.add(projectInfoAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:projectinfo:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ProjectInfoDeleteCmd projectInfoDeleteCmd){
        return projectInfoAppService.delete(projectInfoDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("project:projectinfo:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ProjectInfoPageQry projectInfoPageQry){
        return projectInfoAppService.page(projectInfoPageQry);
    }

    @GetMapping("/list")
    @Permissions("project:projectinfo:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ProjectInfoListQry projectInfoListQry){
        return projectInfoAppService.list(projectInfoListQry);
    }

    @GetMapping("/info")
    @Permissions("project:projectinfo:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ProjectInfoGetQry projectInfoGetQry){
        return projectInfoAppService.getById(projectInfoGetQry);
    }

    @PutMapping("/update")
    @Permissions("project:projectinfo:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ProjectInfoUpdateCmd projectInfoUpdateCmd){
        return projectInfoAppService.update(projectInfoUpdateCmd);
    }
}
