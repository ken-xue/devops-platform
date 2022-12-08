package io.kenxue.devops.adapter.rest.machine;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.machine.MachineGroupAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.*;
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
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@RestController
@Tag(name = "服务器分组模块",description = "包含新增/列表/删除")
@RequestMapping("machine/group")
public class MachineGroupController extends BasicController {
    @Resource
    private MachineGroupAppService machineGroupAppService;

    @PostMapping("/add")
    @Permissions("machine:group:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid MachineGroupAddCmd machineGroupAddCmd) {
        return machineGroupAppService.add(machineGroupAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("machine:group:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid MachineGroupDeleteCmd machineGroupDeleteCmd){
        return machineGroupAppService.delete(machineGroupDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("machine:group:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid MachineGroupPageQry machineGroupPageQry){
        return machineGroupAppService.page(machineGroupPageQry);
    }

    @GetMapping("/list")
    @Permissions("machine:group:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid MachineGroupListQry machineGroupListQry){
        return machineGroupAppService.list(machineGroupListQry);
    }

    @GetMapping("/info")
    @Permissions("machine:group:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid MachineGroupGetQry machineGroupGetQry){
        return machineGroupAppService.getById(machineGroupGetQry);
    }

    @PutMapping("/update")
    @Permissions("machine:group:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody MachineGroupUpdateCmd machineGroupUpdateCmd){
        return machineGroupAppService.update(machineGroupUpdateCmd);
    }
}
