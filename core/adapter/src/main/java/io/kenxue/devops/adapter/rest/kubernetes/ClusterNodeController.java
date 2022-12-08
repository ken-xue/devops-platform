package io.kenxue.devops.adapter.rest.kubernetes;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.kubernetes.ClusterNodeAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.*;
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
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@RestController
@Tag(name = "kubernetes集群节点模块",description = "包含新增/列表/删除")
@RequestMapping("kubernetes/clusternode")
public class ClusterNodeController extends BasicController {
    @Resource
    private ClusterNodeAppService clusterNodeAppService;

    @PostMapping("/add")
    @Permissions("kubernetes:clusternode:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ClusterNodeAddCmd clusterNodeAddCmd) {
        return clusterNodeAppService.add(clusterNodeAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("kubernetes:clusternode:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ClusterNodeDeleteCmd clusterNodeDeleteCmd){
        return clusterNodeAppService.delete(clusterNodeDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("kubernetes:clusternode:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ClusterNodePageQry clusterNodePageQry){
        return clusterNodeAppService.page(clusterNodePageQry);
    }

    @GetMapping("/list")
    @Permissions("kubernetes:clusternode:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ClusterNodeListQry clusterNodeListQry){
        return clusterNodeAppService.list(clusterNodeListQry);
    }

    @GetMapping("/info")
    @Permissions("kubernetes:clusternode:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ClusterNodeGetQry clusterNodeGetQry){
        return clusterNodeAppService.getById(clusterNodeGetQry);
    }

    @PutMapping("/update")
    @Permissions("kubernetes:clusternode:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ClusterNodeUpdateCmd clusterNodeUpdateCmd){
        return clusterNodeAppService.update(clusterNodeUpdateCmd);
    }
}
