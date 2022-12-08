package io.kenxue.devops.adapter.rest.application;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.application.ApplicationInfoAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.*;
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
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@RestController
@Tag(name = "应用模块",description = "包含新增/列表/删除")
@RequestMapping("app/info")
public class ApplicationInfoController extends BasicController {
    @Resource
    private ApplicationInfoAppService applicationInfoAppService;

    @PostMapping("/add")
    @Permissions("application:applicationinfo:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ApplicationInfoAddCmd applicationInfoAddCmd) {
        return applicationInfoAppService.add(applicationInfoAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationinfo:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ApplicationInfoDeleteCmd applicationInfoDeleteCmd){
        return applicationInfoAppService.delete(applicationInfoDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationinfo:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ApplicationInfoPageQry applicationInfoPageQry){
        return applicationInfoAppService.page(applicationInfoPageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationinfo:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ApplicationInfoListQry applicationInfoListQry){
        return applicationInfoAppService.list(applicationInfoListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationinfo:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ApplicationInfoGetQry applicationInfoGetQry){
        return applicationInfoAppService.getById(applicationInfoGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationinfo:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ApplicationInfoUpdateCmd applicationInfoUpdateCmd){
        return applicationInfoAppService.update(applicationInfoUpdateCmd);
    }
}
