package io.kenxue.cicd.adapter.rest.pipeline;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.pipeline.NodeExecuteLoggerAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.*;
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
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@RestController
@Api(tags = "节点执行记录模块",description = "包含新增/列表/删除")
@RequestMapping("pipeline/node/execute/logger")
public class PipelineNodeLoggerController extends BasicController {
    @Resource
    private NodeExecuteLoggerAppService nodeExecuteLoggerAppService;

    @PostMapping("/add")
    @Permissions("pipeline:nodeexecutelogger:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid NodeExecuteLoggerAddCmd nodeExecuteLoggerAddCmd) {
        return nodeExecuteLoggerAppService.add(nodeExecuteLoggerAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("pipeline:nodeexecutelogger:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid NodeExecuteLoggerDeleteCmd nodeExecuteLoggerDeleteCmd){
        return nodeExecuteLoggerAppService.delete(nodeExecuteLoggerDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("pipeline:nodeexecutelogger:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid NodeExecuteLoggerPageQry nodeExecuteLoggerPageQry){
        return nodeExecuteLoggerAppService.page(nodeExecuteLoggerPageQry);
    }

    @GetMapping("/list")
    @Permissions("pipeline:nodeexecutelogger:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid NodeExecuteLoggerListQry nodeExecuteLoggerListQry){
        return nodeExecuteLoggerAppService.list(nodeExecuteLoggerListQry);
    }

    @GetMapping("/info")
    @Permissions("pipeline:nodeexecutelogger:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid NodeExecuteLoggerGetQry nodeExecuteLoggerGetQry){
        return nodeExecuteLoggerAppService.getById(nodeExecuteLoggerGetQry);
    }

    @PutMapping("/update")
    @Permissions("pipeline:nodeexecutelogger:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody NodeExecuteLoggerUpdateCmd nodeExecuteLoggerUpdateCmd){
        return nodeExecuteLoggerAppService.update(nodeExecuteLoggerUpdateCmd);
    }
}
