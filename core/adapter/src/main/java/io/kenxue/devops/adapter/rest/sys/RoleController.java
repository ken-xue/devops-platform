package io.kenxue.devops.adapter.rest.sys;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.sys.RoleAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.role.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@RestController
@Tag(name = "角色表模块",description = "包含新增/列表/删除")
@RequestMapping("sys/role")
public class RoleController extends BasicController {
    @Resource
    private RoleAppService roleAppService;

    @PostMapping("/add")
    @Operation(summary = "添加",method = "POST")
    @Permissions("sys:role:add")
    public Response add(@RequestBody @Valid RoleAddCmd roleAddCmd) {
        return roleAppService.add(roleAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("sys:role:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid RoleDeleteCmd roleDeleteCmd){
        return roleAppService.delete(roleDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("sys:role:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid RolePageQry rolePageQry){
        return roleAppService.page(rolePageQry);
    }

    @GetMapping("/list")
    @Permissions("sys:role:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid RoleListQry roleListQry){
        return roleAppService.list(roleListQry);
    }

    @GetMapping("/info")
    @Permissions("sys:role:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid RoleGetQry roleGetQry){
        return roleAppService.getById(roleGetQry);
    }

    @PutMapping("/update")
    @Permissions("sys:role:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody RoleUpdateCmd roleUpdateCmd){
        return roleAppService.update(roleUpdateCmd);
    }
}
