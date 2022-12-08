package io.kenxue.devops.adapter.rest.pipeline;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.application.ApplicationPipelineAppService;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.*;
import io.kenxue.devops.sharedataboject.common.group.Insert;
import io.kenxue.devops.sharedataboject.pipeline.enums.PipelineTargetEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@RestController
@Tag(name = "流水线模块",description = "包含新增/列表/删除")
@RequestMapping("app/pipeline")
public class PipelineController extends BasicController {

    @Resource
    private ApplicationPipelineAppService applicationPipelineAppService;

    @GetMapping("/webhook/{uuid}")
    @Operation(summary = "详情",method = "GET")
    public Response webhook(@PathVariable String uuid){
        return applicationPipelineAppService.webhook(new PipelineWebhookExecuteCmd().setUuid(uuid));
    }

    @PostMapping("/add")
    @Permissions("application:applicationpipeline:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Validated({Insert.class}) PipelineAddCmd pipelineAddCmd) {
        return applicationPipelineAppService.add(pipelineAddCmd);
    }

    @PostMapping("/deploy")
    @Permissions("application:applicationpipeline:deploy")
    @Operation(summary = "部署",method = "GET")
    public Response deploy(@RequestBody @Valid PipelineDeployCmd pipelineDeployCmd) {
        return applicationPipelineAppService.deploy(pipelineDeployCmd);
    }

    @PostMapping("/execute")
    @Permissions("application:applicationpipeline:execute")
    @Operation(summary = "运行",method = "GET")
    public Response execute(@RequestBody @Valid PipelineExecuteCmd pipelineExecuteCmd) {
        PipelineTargetEnum hand = PipelineTargetEnum.HAND;
        pipelineExecuteCmd.setTargetWay(hand).setTargetUser(UserThreadContext.get());
        return applicationPipelineAppService.execute(pipelineExecuteCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("application:applicationpipeline:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid PipelineDeleteCmd pipelineDeleteCmd){
        return applicationPipelineAppService.delete(pipelineDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("application:applicationpipeline:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid PipelinePageQry pipelinePageQry){
        return applicationPipelineAppService.page(pipelinePageQry);
    }

    @GetMapping("/list")
    @Permissions("application:applicationpipeline:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid PipelineListQry pipelineListQry){
        return applicationPipelineAppService.list(pipelineListQry);
    }

    @GetMapping("/info")
    @Permissions("application:applicationpipeline:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid PipelineGetQry pipelineGetQry){
        return applicationPipelineAppService.getById(pipelineGetQry);
    }

    @PutMapping("/update")
    @Permissions("application:applicationpipeline:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody PipelineUpdateCmd pipelineUpdateCmd){
        return applicationPipelineAppService.update(pipelineUpdateCmd);
    }
}
