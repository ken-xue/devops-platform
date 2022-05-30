package io.kenxue.devops.adapter.rest.machine;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.machine.MachineGroupAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.*;
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
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@RestController
@Api(tags = "服务器分组模块",description = "包含新增/列表/删除")
@RequestMapping("machine/group")
public class MachineGroupController extends BasicController {
    @Resource
    private MachineGroupAppService machineGroupAppService;

    @PostMapping("/add")
    @Permissions("machine:group:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid MachineGroupAddCmd machineGroupAddCmd) {
        return machineGroupAppService.add(machineGroupAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("machine:group:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid MachineGroupDeleteCmd machineGroupDeleteCmd){
        return machineGroupAppService.delete(machineGroupDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("machine:group:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid MachineGroupPageQry machineGroupPageQry){
        return machineGroupAppService.page(machineGroupPageQry);
    }

    @GetMapping("/list")
    @Permissions("machine:group:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid MachineGroupListQry machineGroupListQry){
        return machineGroupAppService.list(machineGroupListQry);
    }

    @GetMapping("/info")
    @Permissions("machine:group:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid MachineGroupGetQry machineGroupGetQry){
        return machineGroupAppService.getById(machineGroupGetQry);
    }

    @PutMapping("/update")
    @Permissions("machine:group:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody MachineGroupUpdateCmd machineGroupUpdateCmd){
        return machineGroupAppService.update(machineGroupUpdateCmd);
    }
}
