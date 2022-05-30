package io.kenxue.devops.adapter.rest.project;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.project.ProjectInfoAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.projectinfo.*;
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
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@RestController
@Api(tags = "项目模块",description = "包含新增/列表/删除")
@RequestMapping("project")
public class ProjectInfoController extends BasicController {
    @Resource
    private ProjectInfoAppService projectInfoAppService;

    @PostMapping("/add")
    @Permissions("project:projectinfo:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ProjectInfoAddCmd projectInfoAddCmd) {
        return projectInfoAppService.add(projectInfoAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("project:projectinfo:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ProjectInfoDeleteCmd projectInfoDeleteCmd){
        return projectInfoAppService.delete(projectInfoDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("project:projectinfo:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ProjectInfoPageQry projectInfoPageQry){
        return projectInfoAppService.page(projectInfoPageQry);
    }

    @GetMapping("/list")
    @Permissions("project:projectinfo:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ProjectInfoListQry projectInfoListQry){
        return projectInfoAppService.list(projectInfoListQry);
    }

    @GetMapping("/info")
    @Permissions("project:projectinfo:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ProjectInfoGetQry projectInfoGetQry){
        return projectInfoAppService.getById(projectInfoGetQry);
    }

    @PutMapping("/update")
    @Permissions("project:projectinfo:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ProjectInfoUpdateCmd projectInfoUpdateCmd){
        return projectInfoAppService.update(projectInfoUpdateCmd);
    }
}
