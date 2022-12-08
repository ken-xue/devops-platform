package io.kenxue.devops.adapter.rest.sys;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.sys.ConfigAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.config.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@RestController
@Tag(name = "系统配置表模块",description = "包含新增/列表/删除")
@RequestMapping("sys/config")
public class ConfigController extends BasicController {
    @Resource
    private ConfigAppService configAppService;

    @PostMapping("/add")
    @Permissions("sys:config:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ConfigAddCmd cmd) {
        return configAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("sys:config:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ConfigDeleteCmd cmd){
        return configAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("sys:config:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ConfigPageQry qry){
        return configAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("sys:config:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ConfigListQry qry){
        return configAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("sys:config:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ConfigGetQry qry){
        return configAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("sys:config:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ConfigUpdateCmd cmd){
        return configAppService.update(cmd);
    }
}
