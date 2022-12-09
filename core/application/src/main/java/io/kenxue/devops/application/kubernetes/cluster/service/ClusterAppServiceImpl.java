package io.kenxue.devops.application.kubernetes.cluster.service;

import io.kenxue.devops.application.kubernetes.cluster.command.ClusterImportCmdExe;
import io.kenxue.devops.application.kubernetes.cluster.command.query.*;
import io.kenxue.devops.coreclient.api.kubernetes.ClusterAppService;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.*;
import io.kenxue.devops.application.kubernetes.cluster.command.ClusterAddCmdExe;
import io.kenxue.devops.application.kubernetes.cluster.command.ClusterDeleteCmdExe;
import io.kenxue.devops.application.kubernetes.cluster.command.ClusterUpdateCmdExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

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
    private ClusterResourceQryExe clusterResourceQryExe;
    @Resource
    private PodDescribeQryExe podDescribeQryExe;
    @Resource
    private ClusterPageQryExe clusterPageQryExe;
    @Resource
    private ClusterDeleteCmdExe clusterDeleteCmdExe;
    @Resource
    private ClusterImportCmdExe clusterImportCmdExe;

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

    /**
     * 导入kubernetes集群
     * @param cmd
     * @return
     */
    @Override
    public Response importing(ClusterImportCmd cmd) {
        return clusterImportCmdExe.execute(cmd);
    }

    @Override
    public Response resources(ClusterResourceQry qry) {
        return clusterResourceQryExe.execute(qry);
    }

    @Override
    public Response describe(ClusterPodDescribeQry qry) {
        return podDescribeQryExe.execute(qry);
    }
}
