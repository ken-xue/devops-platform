package io.kenxue.devops.adapter.rest.application;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.application.ApplicationConfigAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.*;
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
 * @date 2021-12-28 22:57:10
 */
@RestController
@Api(tags = "流水线模块",description = "包含新增/列表/删除")
@RequestMapping("application/applicationconfig")
public class ApplicationConfigController extends BasicController {
    @Resource
    private ApplicationConfigAppService applicationConfigAppService;

    @PostMapping("/add")
    @Permissions("application:applicationconfig:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ApplicationConfigAddCmd applicationConfigAddCmd) {
        return applicationConfigAppService.add(applicationConfigAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationconfig:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ApplicationConfigDeleteCmd applicationConfigDeleteCmd){
        return applicationConfigAppService.delete(applicationConfigDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationconfig:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ApplicationConfigPageQry applicationConfigPageQry){
        return applicationConfigAppService.page(applicationConfigPageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationconfig:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ApplicationConfigListQry applicationConfigListQry){
        return applicationConfigAppService.list(applicationConfigListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationconfig:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ApplicationConfigGetQry applicationConfigGetQry){
        return applicationConfigAppService.getById(applicationConfigGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationconfig:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ApplicationConfigUpdateCmd applicationConfigUpdateCmd){
        return applicationConfigAppService.update(applicationConfigUpdateCmd);
    }
}
