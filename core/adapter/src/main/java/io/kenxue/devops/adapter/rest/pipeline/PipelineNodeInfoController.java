package io.kenxue.devops.adapter.rest.pipeline;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.sharedataboject.common.group.Insert;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.pipeline.PipelineNodeInfoAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.*;
import org.springframework.validation.annotation.Validated;
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
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@RestController
@Api(tags = "流水线节点详情模块",description = "包含新增/列表/删除")
@RequestMapping("pipeline/node/info")
public class PipelineNodeInfoController extends BasicController {
    @Resource
    private PipelineNodeInfoAppService pipelineNodeInfoAppService;

    @PostMapping("/add")
    @Permissions("pipeline:pipelinenodeinfo:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Validated({Insert.class}) PipelineNodeInfoAddCmd pipelineNodeInfoAddCmd) {
        return pipelineNodeInfoAppService.add(pipelineNodeInfoAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("pipeline:pipelinenodeinfo:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid PipelineNodeInfoDeleteCmd pipelineNodeInfoDeleteCmd){
        return pipelineNodeInfoAppService.delete(pipelineNodeInfoDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("pipeline:pipelinenodeinfo:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid PipelineNodeInfoPageQry pipelineNodeInfoPageQry){
        return pipelineNodeInfoAppService.page(pipelineNodeInfoPageQry);
    }

    @GetMapping("/list")
    @Permissions("pipeline:pipelinenodeinfo:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid PipelineNodeInfoListQry pipelineNodeInfoListQry){
        return pipelineNodeInfoAppService.list(pipelineNodeInfoListQry);
    }

    @GetMapping("/info")
    @Permissions("pipeline:pipelinenodeinfo:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid PipelineNodeInfoGetQry pipelineNodeInfoGetQry){
        return pipelineNodeInfoAppService.getById(pipelineNodeInfoGetQry);
    }

    @PutMapping("/update")
    @Permissions("pipeline:pipelinenodeinfo:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody PipelineNodeInfoUpdateCmd pipelineNodeInfoUpdateCmd){
        return pipelineNodeInfoAppService.update(pipelineNodeInfoUpdateCmd);
    }
}
