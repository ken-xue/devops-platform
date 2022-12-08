package io.kenxue.devops.application.middleware.zookeeper.service;

import io.kenxue.devops.application.middleware.zookeeper.command.*;
import io.kenxue.devops.application.middleware.zookeeper.command.query.*;
import io.kenxue.devops.coreclient.api.middleware.ZookeeperAppService;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.*;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.node.*;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Service
public class ZookeeperAppServiceImpl implements ZookeeperAppService {
    @Resource
    private ZookeeperAddCmdExe zookeeperAddCmdExe;
    @Resource
    private ZookeeperUpdateCmdExe zookeeperUpdateCmdExe;
    @Resource
    private ZookeeperGetQryExe zookeeperGetQryExe;
    @Resource
    private ZookeeperListQryExe zookeeperListQryExe;
    @Resource
    private ZookeeperPageQryExe zookeeperPageQryExe;
    @Resource
    private ZookeeperDeleteCmdExe zookeeperDeleteCmdExe;
    @Resource
    private ZookeeperConnCmdExe zookeeperConnCmdExe;
    @Resource
    private ZookeeperTreeQryExe zookeeperTreeQryExe;
    @Resource
    private ZookeeperLazyLeafExe zookeeperLazyLeafExe;
    @Resource
    private ZookeeperAddNodeCmdExe zookeeperAddNodeCmdExe;
    @Resource
    private ZookeeperDetailInfoCmdExe zookeeperDetailInfoCmdExe;
    @Resource
    private ZookeeperNodeDeleteCmdExe zookeeperNodeDeleteCmdExe;
    @Resource
    private ZookeeperNodeUpdateCmdExe zookeeperNodeUpdateCmdExe;

    public Response add(ZookeeperAddCmd zookeeperAddCmd) {
        return  zookeeperAddCmdExe.execute(zookeeperAddCmd);
    }

    public Response update(ZookeeperUpdateCmd cmd) {
        return zookeeperUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ZookeeperDTO> getById(ZookeeperGetQry qry) {
        return zookeeperGetQryExe.execute(qry);
    }

    public MultiResponse<ZookeeperDTO> list(ZookeeperListQry qry) {
        return zookeeperListQryExe.execute(qry);
    }

    public Response delete(ZookeeperDeleteCmd zookeeperDeleteCmd) {
        return zookeeperDeleteCmdExe.execute(zookeeperDeleteCmd);
    }

    public PageResponse<ZookeeperDTO> page(ZookeeperPageQry zookeeperPageQry) {
        return zookeeperPageQryExe.execute(zookeeperPageQry);
    }

    @Override
    public Response testConn(ZookeeperConnCmd zookeeperConnCmd)  {
        return zookeeperConnCmdExe.execute(zookeeperConnCmd);
    }

    @Override
    public SingleResponse<ZookeeperTreeNode> tree(ZookeeperConnCmd ZookeeperConnCmd) {
        return SingleResponse.of(zookeeperTreeQryExe.execute(ZookeeperConnCmd));
    }

    @Override
    public SingleResponse<List<ZookeeperTreeNode>> lazyLeaf(ZookeeperLazyLeafCmd zookeeperLazyLeafCmd) {
        return SingleResponse.of(zookeeperLazyLeafExe.execute(zookeeperLazyLeafCmd));
    }
    @Override
    public Response addZkNode(ZookeeperNodeAddCmd nodeAddCmd) {
        return zookeeperAddNodeCmdExe.execute(nodeAddCmd);
    }

    @Override
    public SingleResponse<ZookeeperDetailInfoDTO> zkNodeDetail(ZookeeperDetailInfoQry zookeeperDetailInfoQry) {
        return SingleResponse.of(zookeeperDetailInfoCmdExe.execute(zookeeperDetailInfoQry));
    }

    @Override
    public Response deleteZkNode(ZookeeperNodeDeleteCmd zookeeperNodeDeleteCmd) {
        return zookeeperNodeDeleteCmdExe.execute(zookeeperNodeDeleteCmd);
    }

    @Override
    public Response updateZkNode(ZookeeperNodeUpdateCmd zookeeperNodeUpdateCmd) {
        return zookeeperNodeUpdateCmdExe.execute(zookeeperNodeUpdateCmd);
    }


}
