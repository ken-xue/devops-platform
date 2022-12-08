package io.kenxue.devops.adapter.rest.application;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.application.ApplicationConfigAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@RestController
@Tag(name = "流水线模块",description = "包含新增/列表/删除")
@RequestMapping("application/applicationconfig")
public class ApplicationConfigController extends BasicController {
    @Resource
    private ApplicationConfigAppService applicationConfigAppService;

    @PostMapping("/add")
    @Permissions("application:applicationconfig:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ApplicationConfigAddCmd applicationConfigAddCmd) {
        return applicationConfigAppService.add(applicationConfigAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationconfig:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ApplicationConfigDeleteCmd applicationConfigDeleteCmd){
        return applicationConfigAppService.delete(applicationConfigDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationconfig:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ApplicationConfigPageQry applicationConfigPageQry){
        return applicationConfigAppService.page(applicationConfigPageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationconfig:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ApplicationConfigListQry applicationConfigListQry){
        return applicationConfigAppService.list(applicationConfigListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationconfig:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ApplicationConfigGetQry applicationConfigGetQry){
        return applicationConfigAppService.getById(applicationConfigGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationconfig:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ApplicationConfigUpdateCmd applicationConfigUpdateCmd){
        return applicationConfigAppService.update(applicationConfigUpdateCmd);
    }
}
