package io.kenxue.devops.domain.repository.kubernetes;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterListQry;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterPageQry;
import io.kenxue.devops.domain.domain.kubernetes.Cluster;
import java.util.List;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
public interface ClusterRepository {
    void create(Cluster cluster);
    void update(Cluster cluster);
    Cluster getById(Long id);
    List<Cluster> list(ClusterListQry clusterListQry);
    Page<Cluster> page(ClusterPageQry qry);
}
