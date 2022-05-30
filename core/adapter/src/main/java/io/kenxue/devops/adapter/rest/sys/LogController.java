package io.kenxue.devops.adapter.rest.sys;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.sys.LogAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.log.*;
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
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@RestController
@Api(tags = "系统日志模块",description = "包含新增/列表/删除")
@RequestMapping("sys/log")
public class LogController extends BasicController {
    @Resource
    private LogAppService logAppService;

    @PostMapping("/add")
    @Permissions("sys:log:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid LogAddCmd logAddCmd) {
        return logAppService.add(logAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("sys:log:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid LogDeleteCmd logDeleteCmd){
        return logAppService.delete(logDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("sys:log:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid LogPageQry logPageQry){
        return logAppService.page(logPageQry);
    }

    @GetMapping("/info")
    @Permissions("sys:log:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid LogGetQry logGetQry){
        return logAppService.getById(logGetQry);
    }

    @PutMapping("/update")
    @Permissions("sys:log:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody LogUpdateCmd logUpdateCmd){
        return logAppService.update(logUpdateCmd);
    }
}
