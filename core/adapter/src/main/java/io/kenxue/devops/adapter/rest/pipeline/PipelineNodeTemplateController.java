package io.kenxue.devops.adapter.rest.pipeline;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.application.PipelineNodeAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@RestController
@Tag(name = "流水线节点类型模块",description = "包含新增/列表/删除")
@RequestMapping("pipeline/node")
public class PipelineNodeTemplateController extends BasicController {
    @Resource
    private PipelineNodeAppService pipelineNodeAppService;

    @PostMapping("/add")
    @Permissions("application:pipelinenode:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid PipelineNodeAddCmd pipelineNodeAddCmd) {
        return pipelineNodeAppService.add(pipelineNodeAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:pipelinenode:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid PipelineNodeDeleteCmd pipelineNodeDeleteCmd){
        return pipelineNodeAppService.delete(pipelineNodeDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:pipelinenode:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid PipelineNodePageQry pipelineNodePageQry){
        return pipelineNodeAppService.page(pipelineNodePageQry);
    }

    @GetMapping("/list")
    @Permissions("application:pipelinenode:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid PipelineNodeListQry pipelineNodeListQry){
        return pipelineNodeAppService.list(pipelineNodeListQry);
    }

    @GetMapping("/info")
    @Permissions("application:pipelinenode:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid PipelineNodeGetQry pipelineNodeGetQry){
        return pipelineNodeAppService.getById(pipelineNodeGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:pipelinenode:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody PipelineNodeUpdateCmd pipelineNodeUpdateCmd){
        return pipelineNodeAppService.update(pipelineNodeUpdateCmd);
    }
}
