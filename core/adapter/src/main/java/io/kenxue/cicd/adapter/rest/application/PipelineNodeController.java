package io.kenxue.cicd.adapter.rest.application;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.application.PipelineNodeAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.pipelinenode.*;
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
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@RestController
@Api(tags = "流水线节点类型模块",description = "包含新增/列表/删除")
@RequestMapping("pipeline/node")
public class PipelineNodeController extends BasicController {
    @Resource
    private PipelineNodeAppService pipelineNodeAppService;

    @PostMapping("/add")
    @Permissions("application:pipelinenode:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid PipelineNodeAddCmd pipelineNodeAddCmd) {
        return pipelineNodeAppService.add(pipelineNodeAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:pipelinenode:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid PipelineNodeDeleteCmd pipelineNodeDeleteCmd){
        return pipelineNodeAppService.delete(pipelineNodeDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:pipelinenode:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid PipelineNodePageQry pipelineNodePageQry){
        return pipelineNodeAppService.page(pipelineNodePageQry);
    }

    @GetMapping("/list")
    @Permissions("application:pipelinenode:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid PipelineNodeListQry pipelineNodeListQry){
        return pipelineNodeAppService.list(pipelineNodeListQry);
    }

    @GetMapping("/info")
    @Permissions("application:pipelinenode:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid PipelineNodeGetQry pipelineNodeGetQry){
        return pipelineNodeAppService.getById(pipelineNodeGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:pipelinenode:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody PipelineNodeUpdateCmd pipelineNodeUpdateCmd){
        return pipelineNodeAppService.update(pipelineNodeUpdateCmd);
    }
}
