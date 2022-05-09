package io.kenxue.cicd.domain.repository.middleware;

import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZkNodeAddCmd;
import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZkTreeNode;
import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZookeeperConnectCmd;
import io.kenxue.cicd.coreclient.dto.common.response.Response;

import java.util.List;

public interface ZookeeperRepository {
    ZkTreeNode connect(ZookeeperConnectCmd zookeeperConnectCmd);

    List<ZkTreeNode> lazyLeaf(String id);

    Response addZkNode(ZkNodeAddCmd nodeAddCmd);
}
