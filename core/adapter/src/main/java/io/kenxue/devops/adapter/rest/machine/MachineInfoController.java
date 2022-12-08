package io.kenxue.devops.adapter.rest.machine;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.application.MachineInfoAppService;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.machine.*;
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
 * 应用服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@RestController
@Tag(name = "服务器节点模块",description = "包含服务器节点新增/列表/删除")
@RequestMapping("app/machine")
public class MachineInfoController extends BasicController {

    @Resource
    private MachineInfoAppService machineInfoAppService;

    @PostMapping("/add")
    @Permissions("application:machineinfo:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid MachineInfoAddCmd machineInfoAddCmd) {
        return machineInfoAppService.add(machineInfoAddCmd);
    }

    @PostMapping("/test/conn/ssh")
    @Permissions("application:machineinfo:add")
    @Operation(summary = "测试连接",method = "POST")
    public Response testConn(@RequestBody @Valid MachineInfoAddCmd machineInfoAddCmd) {
        return machineInfoAppService.testConn(machineInfoAddCmd);
    }

    @PostMapping("/add/secret")
    @Permissions("application:machineinfo:add")
    @Operation(summary = "添加秘钥",method = "POST")
    public Response addSecretKey(@RequestBody @Valid MachineInfoAddCmd machineInfoAddCmd) {
        return machineInfoAppService.addSecretKey(machineInfoAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:machineinfo:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid MachineInfoDeleteCmd machineInfoDeleteCmd){
        return machineInfoAppService.delete(machineInfoDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:machineinfo:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid MachineInfoPageQry machineInfoPageQry){
        PageResponse<MachineInfoDTO> response = machineInfoAppService.page(machineInfoPageQry);
        response.getData().forEach(v->{
            //数据脱敏
            v.setAccessKey(null).setAccessPassword(null);
        });
        return response;
    }

    @GetMapping("/list")
    @Permissions("application:machineinfo:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid MachineInfoListQry machineInfoListQry){
        MultiResponse<MachineInfoDTO> response = machineInfoAppService.list(machineInfoListQry);
        response.getData().forEach(v->{
            v.setAccessKey(null).setAccessPassword(null);//数据脱敏
        });
        return response;
    }

    @GetMapping("/info")
    @Permissions("application:machineinfo:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid MachineInfoGetQry machineInfoGetQry){
        SingleResponse<MachineInfoDTO> response = machineInfoAppService.getById(machineInfoGetQry);
        response.getData().setAccessKey(null);
        response.getData().setAccessPassword(null);
        return response;
    }

    @PutMapping("/update")
    @Permissions("application:machineinfo:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody MachineInfoUpdateCmd machineInfoUpdateCmd){
        return machineInfoAppService.update(machineInfoUpdateCmd);
    }
}
