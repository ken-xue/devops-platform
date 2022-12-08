package io.kenxue.devops.adapter.rest.pipeline;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.pipeline.NodeExecuteLoggerAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.*;
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
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@RestController
@Tag(name = "节点执行记录模块",description = "包含新增/列表/删除")
@RequestMapping("pipeline/node/execute/logger")
public class PipelineNodeLoggerController extends BasicController {
    @Resource
    private NodeExecuteLoggerAppService nodeExecuteLoggerAppService;

    @PostMapping("/add")
    @Permissions("pipeline:nodeexecutelogger:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid NodeExecuteLoggerAddCmd nodeExecuteLoggerAddCmd) {
        return nodeExecuteLoggerAppService.add(nodeExecuteLoggerAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("pipeline:nodeexecutelogger:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid NodeExecuteLoggerDeleteCmd nodeExecuteLoggerDeleteCmd){
        return nodeExecuteLoggerAppService.delete(nodeExecuteLoggerDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("pipeline:nodeexecutelogger:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid NodeExecuteLoggerPageQry nodeExecuteLoggerPageQry){
        return nodeExecuteLoggerAppService.page(nodeExecuteLoggerPageQry);
    }

    @GetMapping("/list")
    @Permissions("pipeline:nodeexecutelogger:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid NodeExecuteLoggerListQry nodeExecuteLoggerListQry){
        return nodeExecuteLoggerAppService.list(nodeExecuteLoggerListQry);
    }

    @GetMapping("/get")
    @Permissions("pipeline:nodeexecutelogger:info")
    @Operation(summary = "详情",method = "GET")
    public Response view(@ModelAttribute @Valid NodeExecuteLoggerGetQry nodeExecuteLoggerGetQry){
        return nodeExecuteLoggerAppService.getNodeAndLoggerUuId(nodeExecuteLoggerGetQry);
    }

    @GetMapping("/info")
    @Permissions("pipeline:nodeexecutelogger:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid NodeExecuteLoggerGetQry nodeExecuteLoggerGetQry){
        return nodeExecuteLoggerAppService.getById(nodeExecuteLoggerGetQry);
    }

    @PutMapping("/update")
    @Permissions("pipeline:nodeexecutelogger:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody NodeExecuteLoggerUpdateCmd nodeExecuteLoggerUpdateCmd){
        return nodeExecuteLoggerAppService.update(nodeExecuteLoggerUpdateCmd);
    }
}
