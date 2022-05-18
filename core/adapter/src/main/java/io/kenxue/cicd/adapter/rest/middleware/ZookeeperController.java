package io.kenxue.cicd.adapter.rest.middleware;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.middleware.ZookeeperAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.*;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.node.*;
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
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@RestController
@Api(tags = "基建中间件zk模块",description = "包含新增/列表/删除")
@RequestMapping("middleware/zookeeper")
public class ZookeeperController extends BasicController {
    @Resource
    private ZookeeperAppService zookeeperAppService;

    @PostMapping("/add")
    @Permissions("middleware:zookeeper:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ZookeeperAddCmd zookeeperAddCmd) {
        return zookeeperAppService.add(zookeeperAddCmd);
    }

    @DeleteMapping("/delete")
    @Permissions("middleware:zookeeper:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ZookeeperDeleteCmd zookeeperDeleteCmd){
        return zookeeperAppService.delete(zookeeperDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("middleware:zookeeper:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ZookeeperPageQry zookeeperPageQry){
        return zookeeperAppService.page(zookeeperPageQry);
    }

    @GetMapping("/list")
    @Permissions("middleware:zookeeper:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ZookeeperListQry zookeeperListQry){
        return zookeeperAppService.list(zookeeperListQry);
    }

    @GetMapping("/info")
    @Permissions("middleware:zookeeper:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ZookeeperGetQry zookeeperGetQry){
        return zookeeperAppService.getById(zookeeperGetQry);
    }

    @PutMapping("/update")
    @Permissions("middleware:zookeeper:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ZookeeperUpdateCmd zookeeperUpdateCmd){
        return zookeeperAppService.update(zookeeperUpdateCmd);
    }

    @PostMapping("/test/conn")
    @Permissions("middleware:zookeeper:update")
    @ApiOperation(value = "测试连接",httpMethod = "POST")
    public Response testConn(@RequestBody ZookeeperConnCmd zookeeperConnCmd) {
        return zookeeperAppService.testConn(zookeeperConnCmd);
    }

    @PostMapping("/tree")
    @Permissions("middleware:zookeeper:update")
    @ApiOperation(value = "树结构展示",httpMethod = "POST")
    public Response tree(@RequestBody ZookeeperConnCmd ZookeeperConnCmd) {
        return zookeeperAppService.tree(ZookeeperConnCmd);
    }

    @PostMapping("/lazyLeaf")
    @ApiOperation(value = "叶子节点懒加载",httpMethod = "POST")
    public Response lazyLeaf(@RequestBody ZookeeperLazyLeafCmd zookeeperLazyLeafCmd){
        return zookeeperAppService.lazyLeaf(zookeeperLazyLeafCmd);
    }

    @PostMapping("/addZkNode")
    @ApiOperation(value = "添加子节点",httpMethod = "POST")
    public Response addZkNode(@RequestBody ZookeeperNodeAddCmd zookeeperNodeAddCmd){
        return zookeeperAppService.addZkNode(zookeeperNodeAddCmd);
    }

    @PostMapping("/zkNodeDetail")
    @ApiOperation(value = "节点详情",httpMethod = "POST")
    public Response zkNodeDetail(@RequestBody ZookeeperDetailInfoQry zookeeperDetailInfoQry){
        return zookeeperAppService.zkNodeDetail(zookeeperDetailInfoQry);
    }

    @PostMapping("/updateZkNode")
    @ApiOperation(value = "更新节点",httpMethod = "POST")
    public Response updateZkNode(@RequestBody ZookeeperNodeUpdateCmd zookeeperNodeUpdateCmd){
        return zookeeperAppService.updateZkNode(zookeeperNodeUpdateCmd);
    }

    @PostMapping("/deleteZkNode")
    @ApiOperation(value = "删除节点",httpMethod = "POST")
    public Response deleteZkNode(@RequestBody ZookeeperNodeDeleteCmd zookeeperNodeDeleteCmd){
        return zookeeperAppService.deleteZkNode(zookeeperNodeDeleteCmd);
    }
}
