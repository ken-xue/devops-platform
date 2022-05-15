package io.kenxue.cicd.application.middleware.zookeeper.service;

import io.kenxue.cicd.application.middleware.zookeeper.command.ZkNodeAddCmdExe;
import io.kenxue.cicd.application.middleware.zookeeper.command.ZkNodeQryCmdExe;
import io.kenxue.cicd.application.middleware.zookeeper.command.ZookeeperConnectCmdExe;
import io.kenxue.cicd.coreclient.api.middleware.ZookeeperAppService;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZkNodeAddCmd;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZkTreeNode;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperConnectCmd;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2117:39
 */
@Service
public class ZookeeperAppServiceImpl implements ZookeeperAppService {
    @Resource
    private ZookeeperConnectCmdExe zookeeperConnectCmdExe;
    @Resource
    private ZkNodeQryCmdExe zkNodeQryCmdExe;
    @Resource
    private ZkNodeAddCmdExe zkNodeAddCmdExe;

    @Override
    public SingleResponse<List<ZkTreeNode>> connect(ZookeeperConnectCmd zookeeperConnectCmd) {
        ZkTreeNode treeNode = zookeeperConnectCmdExe.execute(zookeeperConnectCmd);
        ArrayList<ZkTreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(treeNode);
        return SingleResponse.of(treeNodes);
    }

    @Override
    public SingleResponse<List<ZkTreeNode>> lazyLeaf(String id) {
        List<ZkTreeNode> treeNodeList = zkNodeQryCmdExe.execute(id);
        return SingleResponse.of(treeNodeList);
    }

    @Override
    public Response addZkNode(ZkNodeAddCmd nodeAddCmd) {
        return zkNodeAddCmdExe.execute(nodeAddCmd);
    }
}
