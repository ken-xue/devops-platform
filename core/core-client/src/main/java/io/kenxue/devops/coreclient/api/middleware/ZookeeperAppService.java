package io.kenxue.devops.coreclient.api.middleware;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.*;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.node.*;

import java.util.List;

/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
public interface ZookeeperAppService {
    Response add(ZookeeperAddCmd cmd);
    Response update(ZookeeperUpdateCmd cmd);
    Response delete(ZookeeperDeleteCmd userDeleteCmd);
    SingleResponse<ZookeeperDTO> getById(ZookeeperGetQry qry);
    MultiResponse<ZookeeperDTO> list(ZookeeperListQry qry);
    PageResponse<ZookeeperDTO> page(ZookeeperPageQry userPageQry);
    Response testConn(ZookeeperConnCmd zookeeperConnCmd);
    SingleResponse<ZookeeperTreeNode> tree(ZookeeperConnCmd ZookeeperConnCmd);
    SingleResponse<List<ZookeeperTreeNode>> lazyLeaf(ZookeeperLazyLeafCmd zookeeperLazyLeafCmd);
    Response addZkNode(ZookeeperNodeAddCmd nodeAddCmd);
    SingleResponse<ZookeeperDetailInfoDTO> zkNodeDetail(ZookeeperDetailInfoQry zookeeperDetailInfoQry);
    Response deleteZkNode(ZookeeperNodeDeleteCmd zookeeperNodeDeleteCmd);
    Response updateZkNode(ZookeeperNodeUpdateCmd zookeeperNodeUpdateCmd);
}
