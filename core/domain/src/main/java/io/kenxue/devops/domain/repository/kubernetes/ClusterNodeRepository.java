package io.kenxue.devops.domain.repository.kubernetes;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeListQry;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodePageQry;
import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import java.util.List;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
public interface ClusterNodeRepository {
    void create(ClusterNode clusterNode);
    void update(ClusterNode clusterNode);
    ClusterNode getById(Long id);
    List<ClusterNode> list(ClusterNodeListQry clusterNodeListQry);
    Page<ClusterNode> page(ClusterNodePageQry qry);
}
