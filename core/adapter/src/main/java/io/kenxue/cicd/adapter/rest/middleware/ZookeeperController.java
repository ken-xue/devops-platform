package io.kenxue.cicd.adapter.rest.middleware;

import io.kenxue.cicd.coreclient.api.middleware.ZookeeperAppService;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZkNodeAddCmd;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperConnectCmd;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 刘牌
 * @Title: ZookeeperController
 * @date 2022/2/2117:19
 */
@RestController
@Api(tags = "zookeeper模块",description = "包含新增/列表/删除")
@RequestMapping("/base/zookeeper")
public class ZookeeperController {

    @Resource
    private ZookeeperAppService zookeeperAppService;

    @PostMapping("/connect")
    @ApiOperation(value = "zookeeper连接",httpMethod = "POST")
    public Response connect(@RequestBody ZookeeperConnectCmd zookeeperConnectCmd){
        return zookeeperAppService.connect(zookeeperConnectCmd);
    }

    @GetMapping("/lazyLeaf")
    @ApiOperation(value = "叶子节点懒加载",httpMethod = "GET")
    public Response lazyLeaf(@RequestParam("id") String id){
        return zookeeperAppService.lazyLeaf(id);
    }

    @PostMapping("/addZkNode")
    @ApiOperation(value = "添加子节点",httpMethod = "POST")
    public Response addZkNode(@RequestBody ZkNodeAddCmd nodeAddCmd){
        return zookeeperAppService.addZkNode(nodeAddCmd);
    }
}
