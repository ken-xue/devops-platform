package io.kenxue.devops.adapter.rest.middleware;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.middleware.ZookeeperAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.*;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.node.*;
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
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@RestController
@Tag(name = "基建中间件zk模块",description = "包含新增/列表/删除")
@RequestMapping("middleware/zookeeper")
public class ZookeeperController extends BasicController {
    @Resource
    private ZookeeperAppService zookeeperAppService;

    @PostMapping("/add")
    @Permissions("middleware:zookeeper:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ZookeeperAddCmd zookeeperAddCmd) {
        return zookeeperAppService.add(zookeeperAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("middleware:zookeeper:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ZookeeperDeleteCmd zookeeperDeleteCmd){
        return zookeeperAppService.delete(zookeeperDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("middleware:zookeeper:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ZookeeperPageQry zookeeperPageQry){
        return zookeeperAppService.page(zookeeperPageQry);
    }

    @GetMapping("/list")
    @Permissions("middleware:zookeeper:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ZookeeperListQry zookeeperListQry){
        return zookeeperAppService.list(zookeeperListQry);
    }

    @GetMapping("/info")
    @Permissions("middleware:zookeeper:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ZookeeperGetQry zookeeperGetQry){
        return zookeeperAppService.getById(zookeeperGetQry);
    }

    @PutMapping("/update")
    @Permissions("middleware:zookeeper:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ZookeeperUpdateCmd zookeeperUpdateCmd){
        return zookeeperAppService.update(zookeeperUpdateCmd);
    }

    @PostMapping("/test/conn")
    @Permissions("middleware:zookeeper:update")
    @Operation(summary = "测试连接",method = "POST")
    public Response testConn(@RequestBody ZookeeperConnCmd zookeeperConnCmd) {
        return zookeeperAppService.testConn(zookeeperConnCmd);
    }

    @PostMapping("/tree")
    @Permissions("middleware:zookeeper:update")
    @Operation(summary = "树结构展示",method = "POST")
    public Response tree(@RequestBody ZookeeperConnCmd ZookeeperConnCmd) {
        return zookeeperAppService.tree(ZookeeperConnCmd);
    }

    @PostMapping("/lazyLeaf")
    @Operation(summary = "叶子节点懒加载",method = "POST")
    public Response lazyLeaf(@RequestBody ZookeeperLazyLeafCmd zookeeperLazyLeafCmd){
        return zookeeperAppService.lazyLeaf(zookeeperLazyLeafCmd);
    }

    @PostMapping("/addZkNode")
    @Operation(summary = "添加子节点",method = "POST")
    public Response addZkNode(@RequestBody ZookeeperNodeAddCmd zookeeperNodeAddCmd){
        return zookeeperAppService.addZkNode(zookeeperNodeAddCmd);
    }

    @PostMapping("/zkNodeDetail")
    @Operation(summary = "节点详情",method = "POST")
    public Response zkNodeDetail(@RequestBody ZookeeperDetailInfoQry zookeeperDetailInfoQry){
        return zookeeperAppService.zkNodeDetail(zookeeperDetailInfoQry);
    }

    @PostMapping("/updateZkNode")
    @Operation(summary = "更新节点",method = "POST")
    public Response updateZkNode(@RequestBody ZookeeperNodeUpdateCmd zookeeperNodeUpdateCmd){
        return zookeeperAppService.updateZkNode(zookeeperNodeUpdateCmd);
    }

    @PostMapping("/deleteZkNode")
    @Operation(summary = "删除节点",method = "POST")
    public Response deleteZkNode(@RequestBody ZookeeperNodeDeleteCmd zookeeperNodeDeleteCmd){
        return zookeeperAppService.deleteZkNode(zookeeperNodeDeleteCmd);
    }
}
