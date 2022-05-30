package io.kenxue.devops.coreclient.api.kubernetes;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.*;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
public interface ClusterNodeAppService {
    Response add(ClusterNodeAddCmd cmd);
    Response update(ClusterNodeUpdateCmd cmd);
    Response delete(ClusterNodeDeleteCmd userDeleteCmd);
    SingleResponse<ClusterNodeDTO> getById(ClusterNodeGetQry qry);
    MultiResponse<ClusterNodeDTO> list(ClusterNodeListQry qry);
    PageResponse<ClusterNodeDTO> page(ClusterNodePageQry userPageQry);
}
