package io.kenxue.devops.adapter.rest.pipeline;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.pipeline.PipelineExecuteLoggerAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@RestController
@Tag(name = "流水线执行记录模块",description = "包含新增/列表/删除")
@RequestMapping("pipeline/execute/logger")
public class PipelineLoggerController extends BasicController {
    @Resource
    private PipelineExecuteLoggerAppService pipelineExecuteLoggerAppService;

    @DeleteMapping("/delete")
    @Permissions("pipeline:logger:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid PipelineExecuteLoggerDeleteCmd pipelineExecuteLoggerDeleteCmd){
        return pipelineExecuteLoggerAppService.delete(pipelineExecuteLoggerDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("pipeline:logger:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid PipelineExecuteLoggerPageQry pipelineExecuteLoggerPageQry){
        return pipelineExecuteLoggerAppService.page(pipelineExecuteLoggerPageQry);
    }

    @GetMapping("/list")
    @Permissions("pipeline:logger:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid PipelineExecuteLoggerListQry pipelineExecuteLoggerListQry){
        return pipelineExecuteLoggerAppService.list(pipelineExecuteLoggerListQry);
    }

    @GetMapping("/info")
    @Permissions("pipeline:logger:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid PipelineExecuteLoggerGetQry pipelineExecuteLoggerGetQry){
        return pipelineExecuteLoggerAppService.getById(pipelineExecuteLoggerGetQry);
    }

    @PutMapping("/update")
    @Permissions("pipeline:logger:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody PipelineExecuteLoggerUpdateCmd pipelineExecuteLoggerUpdateCmd){
        return pipelineExecuteLoggerAppService.update(pipelineExecuteLoggerUpdateCmd);
    }
}
