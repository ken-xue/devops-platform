package io.kenxue.devops.adapter.rest.sys;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.sys.LogAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.log.*;
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
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@RestController
@Tag(name = "系统日志模块",description = "包含新增/列表/删除")
@RequestMapping("sys/log")
public class LogController extends BasicController {
    @Resource
    private LogAppService logAppService;

    @PostMapping("/add")
    @Permissions("sys:log:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid LogAddCmd logAddCmd) {
        return logAppService.add(logAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("sys:log:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid LogDeleteCmd logDeleteCmd){
        return logAppService.delete(logDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("sys:log:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid LogPageQry logPageQry){
        return logAppService.page(logPageQry);
    }

    @GetMapping("/info")
    @Permissions("sys:log:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid LogGetQry logGetQry){
        return logAppService.getById(logGetQry);
    }

    @PutMapping("/update")
    @Permissions("sys:log:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody LogUpdateCmd logUpdateCmd){
        return logAppService.update(logUpdateCmd);
    }
}
