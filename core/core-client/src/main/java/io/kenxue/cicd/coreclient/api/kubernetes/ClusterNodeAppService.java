package io.kenxue.cicd.coreclient.api.kubernetes;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.*;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
public interface ClusterNodeAppService {
    Response add(ClusterNodeAddCmd cmd);
    Response update(ClusterNodeUpdateCmd cmd);
    Response delete(ClusterNodeDeleteCmd userDeleteCmd);
    SingleResponse<ClusterNodeDTO> getById(ClusterNodeGetQry qry);
    MultiResponse<ClusterNodeDTO> list(ClusterNodeListQry qry);
    PageResponse<ClusterNodeDTO> page(ClusterNodePageQry userPageQry);
}
