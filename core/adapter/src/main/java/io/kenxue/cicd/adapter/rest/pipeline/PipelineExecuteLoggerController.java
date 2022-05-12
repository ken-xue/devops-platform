package io.kenxue.cicd.adapter.rest.pipeline;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.pipeline.PipelineExecuteLoggerAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.*;
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
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@RestController
@Api(tags = "流水线执行记录模块",description = "包含新增/列表/删除")
@RequestMapping("pipeline/execute/logger")
public class PipelineExecuteLoggerController extends BasicController {
    @Resource
    private PipelineExecuteLoggerAppService pipelineExecuteLoggerAppService;

    @DeleteMapping("/delete")
    @Permissions("pipeline:logger:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid PipelineExecuteLoggerDeleteCmd pipelineExecuteLoggerDeleteCmd){
        return pipelineExecuteLoggerAppService.delete(pipelineExecuteLoggerDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("pipeline:logger:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid PipelineExecuteLoggerPageQry pipelineExecuteLoggerPageQry){
        return pipelineExecuteLoggerAppService.page(pipelineExecuteLoggerPageQry);
    }

    @GetMapping("/list")
    @Permissions("pipeline:logger:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid PipelineExecuteLoggerListQry pipelineExecuteLoggerListQry){
        return pipelineExecuteLoggerAppService.list(pipelineExecuteLoggerListQry);
    }

    @GetMapping("/info")
    @Permissions("pipeline:logger:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid PipelineExecuteLoggerGetQry pipelineExecuteLoggerGetQry){
        return pipelineExecuteLoggerAppService.getById(pipelineExecuteLoggerGetQry);
    }

    @PutMapping("/update")
    @Permissions("pipeline:logger:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody PipelineExecuteLoggerUpdateCmd pipelineExecuteLoggerUpdateCmd){
        return pipelineExecuteLoggerAppService.update(pipelineExecuteLoggerUpdateCmd);
    }
}
