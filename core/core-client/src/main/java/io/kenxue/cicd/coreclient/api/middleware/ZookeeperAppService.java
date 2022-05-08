package io.kenxue.cicd.coreclient.api.middleware;

import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZkNodeAddCmd;
import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZkTreeNode;
import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZookeeperConnectCmd;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;

import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2117:40
 */
public interface ZookeeperAppService {
    SingleResponse<List<ZkTreeNode>> connect(ZookeeperConnectCmd zookeeperConnectCmd);

    SingleResponse<List<ZkTreeNode>> lazyLeaf(String id);

    Response addZkNode(ZkNodeAddCmd nodeAddCmd);
}
