package io.kenxue.cicd.adapter.rest.pipeline;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.application.ApplicationPipelineAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.*;
import io.kenxue.cicd.sharedataboject.common.group.Insert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@RestController
@Api(tags = "流水线模块",description = "包含新增/列表/删除")
@RequestMapping("app/pipeline")
public class PipelineController extends BasicController {

    @Resource
    private ApplicationPipelineAppService applicationPipelineAppService;

    @PostMapping("/add")
    @Permissions("application:applicationpipeline:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Validated({Insert.class}) PipelineAddCmd pipelineAddCmd) {
        return applicationPipelineAppService.add(pipelineAddCmd);
    }

    @PostMapping("/deploy")
    @Permissions("application:applicationpipeline:deploy")
    @ApiOperation(value = "部署",httpMethod = "GET")
    public Response deploy(@RequestBody @Valid PipelineDeployCmd pipelineDeployCmd) {
        return applicationPipelineAppService.deploy(pipelineDeployCmd);
    }

    @PostMapping("/execute")
    @Permissions("application:applicationpipeline:execute")
    @ApiOperation(value = "运行",httpMethod = "GET")
    public Response execute(@RequestBody @Valid PipelineExecuteCmd pipelineExecuteCmd) {
        return applicationPipelineAppService.execute(pipelineExecuteCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationpipeline:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid PipelineDeleteCmd pipelineDeleteCmd){
        return applicationPipelineAppService.delete(pipelineDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationpipeline:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid PipelinePageQry pipelinePageQry){
        return applicationPipelineAppService.page(pipelinePageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationpipeline:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid PipelineListQry pipelineListQry){
        return applicationPipelineAppService.list(pipelineListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationpipeline:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid PipelineGetQry pipelineGetQry){
        return applicationPipelineAppService.getById(pipelineGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationpipeline:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody PipelineUpdateCmd pipelineUpdateCmd){
        return applicationPipelineAppService.update(pipelineUpdateCmd);
    }
}
