package io.kenxue.cicd.domain.repository.middleware;

import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZkNodeAddCmd;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZkTreeNode;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperConnectCmd;
import io.kenxue.cicd.coreclient.dto.common.response.Response;

import java.util.List;

public interface ZookeeperRepository {
    ZkTreeNode connect(ZookeeperConnectCmd zookeeperConnectCmd);

    List<ZkTreeNode> lazyLeaf(String id);

    Response addZkNode(ZkNodeAddCmd nodeAddCmd);
}
