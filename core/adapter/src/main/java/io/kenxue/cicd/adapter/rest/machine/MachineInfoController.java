package io.kenxue.cicd.adapter.rest.machine;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.application.MachineInfoAppService;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.*;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
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
 * 应用服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@RestController
@Api(tags = "服务器节点模块",description = "包含服务器节点新增/列表/删除")
@RequestMapping("app/machine")
public class MachineInfoController extends BasicController {

    @Resource
    private MachineInfoAppService machineInfoAppService;

    @PostMapping("/add")
    @Permissions("application:machineinfo:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid MachineInfoAddCmd machineInfoAddCmd) {
        return machineInfoAppService.add(machineInfoAddCmd);
    }

    @PostMapping("/test/conn/ssh")
    @Permissions("application:machineinfo:add")
    @ApiOperation(value = "测试连接",httpMethod = "POST")
    public Response testConn(@RequestBody @Valid MachineInfoAddCmd machineInfoAddCmd) {
        return machineInfoAppService.testConn(machineInfoAddCmd);
    }

    @PostMapping("/add/secret")
    @Permissions("application:machineinfo:add")
    @ApiOperation(value = "添加秘钥",httpMethod = "POST")
    public Response addSecretKey(@RequestBody @Valid MachineInfoAddCmd machineInfoAddCmd) {
        return machineInfoAppService.addSecretKey(machineInfoAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:machineinfo:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid MachineInfoDeleteCmd machineInfoDeleteCmd){
        return machineInfoAppService.delete(machineInfoDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:machineinfo:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid MachineInfoPageQry machineInfoPageQry){
        PageResponse<MachineInfoDTO> response = machineInfoAppService.page(machineInfoPageQry);
        response.getData().forEach(v->{
            //数据脱敏
            v.setAccessKey(null);
            v.setAccessPassword(null);
        });
        return response;
    }

    @GetMapping("/list")
    @Permissions("application:machineinfo:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid MachineInfoListQry machineInfoListQry){
        MultiResponse<MachineInfoDTO> response = machineInfoAppService.list(machineInfoListQry);
        response.getData().forEach(v->{
            //数据脱敏
            v.setAccessKey(null);
            v.setAccessPassword(null);
        });
        return response;
    }

    @GetMapping("/info")
    @Permissions("application:machineinfo:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid MachineInfoGetQry machineInfoGetQry){
        SingleResponse<MachineInfoDTO> response = machineInfoAppService.getById(machineInfoGetQry);
        response.getData().setAccessKey(null);
        response.getData().setAccessPassword(null);
        return response;
    }

    @PutMapping("/update")
    @Permissions("application:machineinfo:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody MachineInfoUpdateCmd machineInfoUpdateCmd){
        return machineInfoAppService.update(machineInfoUpdateCmd);
    }
}
