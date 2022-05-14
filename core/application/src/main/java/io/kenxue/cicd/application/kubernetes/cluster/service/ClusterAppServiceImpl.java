package io.kenxue.cicd.application.kubernetes.cluster.service;

import io.kenxue.cicd.coreclient.api.kubernetes.ClusterAppService;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.*;
import io.kenxue.cicd.application.kubernetes.cluster.command.ClusterAddCmdExe;
import io.kenxue.cicd.application.kubernetes.cluster.command.ClusterDeleteCmdExe;
import io.kenxue.cicd.application.kubernetes.cluster.command.ClusterUpdateCmdExe;
import io.kenxue.cicd.application.kubernetes.cluster.command.query.ClusterGetQryExe;
import io.kenxue.cicd.application.kubernetes.cluster.command.query.ClusterListQryExe;
import io.kenxue.cicd.application.kubernetes.cluster.command.query.ClusterPageQryExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Service
public class ClusterAppServiceImpl implements ClusterAppService {
    @Resource
    private ClusterAddCmdExe clusterAddCmdExe;
    @Resource
    private ClusterUpdateCmdExe clusterUpdateCmdExe;
    @Resource
    private ClusterGetQryExe clusterGetQryExe;
    @Resource
    private ClusterListQryExe clusterListQryExe;
    @Resource
    private ClusterPageQryExe clusterPageQryExe;
    @Resource
    private ClusterDeleteCmdExe clusterDeleteCmdExe;

    public Response add(ClusterAddCmd clusterAddCmd) {
        return  clusterAddCmdExe.execute(clusterAddCmd);
    }

    public Response update(ClusterUpdateCmd cmd) {
        return clusterUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ClusterDTO> getById(ClusterGetQry qry) {
        return clusterGetQryExe.execute(qry);
    }

    public MultiResponse<ClusterDTO> list(ClusterListQry qry) {
        return clusterListQryExe.execute(qry);
    }

    public Response delete(ClusterDeleteCmd clusterDeleteCmd) {
        return clusterDeleteCmdExe.execute(clusterDeleteCmd);
    }

    public PageResponse<ClusterDTO> page(ClusterPageQry clusterPageQry) {
        return clusterPageQryExe.execute(clusterPageQry);
    }

}
