package io.kenxue.cicd.adapter.rest.application;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.application.ApplicationConfigAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationconfig.*;
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
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@RestController
@Api(tags = "流水线模块",description = "包含用户新增/列表/删除")
@RequestMapping("application/applicationconfig")
public class ApplicationConfigController extends BasicController {
    @Resource
    private ApplicationConfigAppService applicationConfigAppService;

    @PostMapping("/add")
    @Permissions("application:applicationConfig:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ApplicationConfigAddCmd applicationConfigAddCmd) {
        return applicationConfigAppService.add(applicationConfigAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationConfig:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ApplicationConfigDeleteCmd applicationConfigDeleteCmd){
        return applicationConfigAppService.delete(applicationConfigDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationConfig:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ApplicationConfigPageQry applicationConfigPageQry){
        return applicationConfigAppService.page(applicationConfigPageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationConfig:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ApplicationConfigListQry applicationConfigListQry){
        return applicationConfigAppService.list(applicationConfigListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationConfig:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ApplicationConfigGetQry applicationConfigGetQry){
        return applicationConfigAppService.getById(applicationConfigGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationConfig:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ApplicationConfigUpdateCmd applicationConfigUpdateCmd){
        return applicationConfigAppService.update(applicationConfigUpdateCmd);
    }
}
