package io.kenxue.devops.application.kubernetes.cluster.command.query.resource;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterResourceQry;

/**
 * 资源查询统一接口 see: {@link io.kenxue.devops.sharedataboject.kubernetes.enums.ResourceEnum}
 */
public interface ClusterResourceQryI {
    MultiResponse execute(ClusterResourceQry qry);
}
