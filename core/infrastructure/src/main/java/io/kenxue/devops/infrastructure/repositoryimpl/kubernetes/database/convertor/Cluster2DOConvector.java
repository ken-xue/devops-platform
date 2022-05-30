package io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.convertor;

import io.kenxue.devops.domain.domain.kubernetes.Cluster;
import io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterDO;
import org.springframework.stereotype.Component;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
public class Cluster2DOConvector implements Convector<Cluster,ClusterDO>{
    
    public ClusterDO toDO(Cluster cluster) {
        return Cluster2DOMapStruct.INSTANCE.toDO(cluster);
    }

    public Cluster toDomain(ClusterDO clusterDO) {
        return Cluster2DOMapStruct.INSTANCE.toDomain(clusterDO);
    }

    public List<ClusterDO> toDOList(List<Cluster> clusterList) {
        return Cluster2DOMapStruct.INSTANCE.toDOList(clusterList);
    }

    public List<Cluster> toDomainList(List<ClusterDO> clusterDOList) {
        return Cluster2DOMapStruct.INSTANCE.toDomainList(clusterDOList);
    }
}
