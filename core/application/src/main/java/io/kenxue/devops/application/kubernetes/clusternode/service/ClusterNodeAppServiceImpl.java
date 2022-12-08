package io.kenxue.devops.application.kubernetes.clusternode.service;

import io.kenxue.devops.coreclient.api.kubernetes.ClusterNodeAppService;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.*;
import io.kenxue.devops.application.kubernetes.clusternode.command.ClusterNodeAddCmdExe;
import io.kenxue.devops.application.kubernetes.clusternode.command.ClusterNodeDeleteCmdExe;
import io.kenxue.devops.application.kubernetes.clusternode.command.ClusterNodeUpdateCmdExe;
import io.kenxue.devops.application.kubernetes.clusternode.command.query.ClusterNodeGetQryExe;
import io.kenxue.devops.application.kubernetes.clusternode.command.query.ClusterNodeListQryExe;
import io.kenxue.devops.application.kubernetes.clusternode.command.query.ClusterNodePageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Service
public class ClusterNodeAppServiceImpl implements ClusterNodeAppService {
    @Resource
    private ClusterNodeAddCmdExe clusterNodeAddCmdExe;
    @Resource
    private ClusterNodeUpdateCmdExe clusterNodeUpdateCmdExe;
    @Resource
    private ClusterNodeGetQryExe clusterNodeGetQryExe;
    @Resource
    private ClusterNodeListQryExe clusterNodeListQryExe;
    @Resource
    private ClusterNodePageQryExe clusterNodePageQryExe;
    @Resource
    private ClusterNodeDeleteCmdExe clusterNodeDeleteCmdExe;

    public Response add(ClusterNodeAddCmd clusterNodeAddCmd) {
        return  clusterNodeAddCmdExe.execute(clusterNodeAddCmd);
    }

    public Response update(ClusterNodeUpdateCmd cmd) {
        return clusterNodeUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ClusterNodeDTO> getById(ClusterNodeGetQry qry) {
        return clusterNodeGetQryExe.execute(qry);
    }

    public MultiResponse<ClusterNodeDTO> list(ClusterNodeListQry qry) {
        return clusterNodeListQryExe.execute(qry);
    }

    public Response delete(ClusterNodeDeleteCmd clusterNodeDeleteCmd) {
        return clusterNodeDeleteCmdExe.execute(clusterNodeDeleteCmd);
    }

    public PageResponse<ClusterNodeDTO> page(ClusterNodePageQry clusterNodePageQry) {
        return clusterNodePageQryExe.execute(clusterNodePageQry);
    }

}
