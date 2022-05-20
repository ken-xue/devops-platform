package io.kenxue.cicd.adapter.rest.kubernetes;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.kubernetes.ClusterNodeAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.*;
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
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@RestController
@Api(tags = "kubernetes集群节点模块",description = "包含新增/列表/删除")
@RequestMapping("kubernetes/clusternode")
public class ClusterNodeController extends BasicController {
    @Resource
    private ClusterNodeAppService clusterNodeAppService;

    @PostMapping("/add")
    @Permissions("kubernetes:clusternode:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ClusterNodeAddCmd clusterNodeAddCmd) {
        return clusterNodeAppService.add(clusterNodeAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("kubernetes:clusternode:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ClusterNodeDeleteCmd clusterNodeDeleteCmd){
        return clusterNodeAppService.delete(clusterNodeDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("kubernetes:clusternode:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ClusterNodePageQry clusterNodePageQry){
        return clusterNodeAppService.page(clusterNodePageQry);
    }

    @GetMapping("/list")
    @Permissions("kubernetes:clusternode:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ClusterNodeListQry clusterNodeListQry){
        return clusterNodeAppService.list(clusterNodeListQry);
    }

    @GetMapping("/info")
    @Permissions("kubernetes:clusternode:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ClusterNodeGetQry clusterNodeGetQry){
        return clusterNodeAppService.getById(clusterNodeGetQry);
    }

    @PutMapping("/update")
    @Permissions("kubernetes:clusternode:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ClusterNodeUpdateCmd clusterNodeUpdateCmd){
        return clusterNodeAppService.update(clusterNodeUpdateCmd);
    }
}
