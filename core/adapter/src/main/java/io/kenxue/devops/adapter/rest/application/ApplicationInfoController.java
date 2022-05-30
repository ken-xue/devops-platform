package io.kenxue.devops.adapter.rest.application;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.application.ApplicationInfoAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.*;
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
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@RestController
@Api(tags = "应用模块",description = "包含新增/列表/删除")
@RequestMapping("app/info")
public class ApplicationInfoController extends BasicController {
    @Resource
    private ApplicationInfoAppService applicationInfoAppService;

    @PostMapping("/add")
    @Permissions("application:applicationinfo:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ApplicationInfoAddCmd applicationInfoAddCmd) {
        return applicationInfoAppService.add(applicationInfoAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationinfo:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ApplicationInfoDeleteCmd applicationInfoDeleteCmd){
        return applicationInfoAppService.delete(applicationInfoDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationinfo:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ApplicationInfoPageQry applicationInfoPageQry){
        return applicationInfoAppService.page(applicationInfoPageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationinfo:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ApplicationInfoListQry applicationInfoListQry){
        return applicationInfoAppService.list(applicationInfoListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationinfo:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ApplicationInfoGetQry applicationInfoGetQry){
        return applicationInfoAppService.getById(applicationInfoGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationinfo:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ApplicationInfoUpdateCmd applicationInfoUpdateCmd){
        return applicationInfoAppService.update(applicationInfoUpdateCmd);
    }
}
