package io.kenxue.cicd.coreclient.api.kubernetes;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.*;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
public interface ClusterAppService {
    Response add(ClusterAddCmd cmd);
    Response update(ClusterUpdateCmd cmd);
    Response delete(ClusterDeleteCmd userDeleteCmd);
    SingleResponse<ClusterDTO> getById(ClusterGetQry qry);
    MultiResponse<ClusterDTO> list(ClusterListQry qry);
    PageResponse<ClusterDTO> page(ClusterPageQry userPageQry);

    /**
     * 通过系统创建kubernetes集群
     * @param clusterCreateCmd
     * @return
     */
    Response create(ClusterCreateCmd clusterCreateCmd);
}
