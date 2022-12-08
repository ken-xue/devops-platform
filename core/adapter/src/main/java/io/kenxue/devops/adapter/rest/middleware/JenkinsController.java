package io.kenxue.devops.adapter.rest.middleware;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.middleware.JenkinsAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.*;
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
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@RestController
@Tag(name = "基建中间件Jenkins模块",description = "包含新增/列表/删除")
@RequestMapping("middleware/jenkins")
public class JenkinsController extends BasicController {
    @Resource
    private JenkinsAppService jenkinsAppService;

    @PostMapping("/add")
    @Permissions("jenkins:jenkins:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid JenkinsAddCmd jenkinsAddCmd) {
        return jenkinsAppService.add(jenkinsAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("jenkins:jenkins:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid JenkinsDeleteCmd jenkinsDeleteCmd){
        return jenkinsAppService.delete(jenkinsDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("jenkins:jenkins:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid JenkinsPageQry jenkinsPageQry){
        return jenkinsAppService.page(jenkinsPageQry);
    }

    @GetMapping("/list")
    @Permissions("jenkins:jenkins:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid JenkinsListQry jenkinsListQry){
        return jenkinsAppService.list(jenkinsListQry);
    }

    @GetMapping("/info")
    @Permissions("jenkins:jenkins:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid JenkinsGetQry jenkinsGetQry){
        return jenkinsAppService.getById(jenkinsGetQry);
    }

    @PutMapping("/update")
    @Permissions("jenkins:jenkins:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody JenkinsUpdateCmd jenkinsUpdateCmd){
        return jenkinsAppService.update(jenkinsUpdateCmd);
    }
}
