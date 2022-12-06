package io.kenxue.devops.coreclient.api.kubernetes;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.*;
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
     * 导入kubernetes集群
     * @param cmd
     * @return
     */
    Response importing(ClusterImportCmd cmd);

    /**
     * pod列表
     * @param clusterPodListQry
     * @return
     */
    Response podList(ClusterPodListQry clusterPodListQry);

    Response describe(ClusterPodDescribeQry qry);
}
