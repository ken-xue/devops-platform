package io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.convertor;

import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterNodeDO;
import org.springframework.stereotype.Component;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterNode2DOConvector implements Convector<ClusterNode,ClusterNodeDO>{
    
    public ClusterNodeDO toDO(ClusterNode clusterNode) {
        return ClusterNode2DOMapStruct.INSTANCE.toDO(clusterNode);
    }

    public ClusterNode toDomain(ClusterNodeDO clusterNodeDO) {
        return ClusterNode2DOMapStruct.INSTANCE.toDomain(clusterNodeDO);
    }

    public List<ClusterNodeDO> toDOList(List<ClusterNode> clusterNodeList) {
        return ClusterNode2DOMapStruct.INSTANCE.toDOList(clusterNodeList);
    }

    public List<ClusterNode> toDomainList(List<ClusterNodeDO> clusterNodeDOList) {
        return ClusterNode2DOMapStruct.INSTANCE.toDomainList(clusterNodeDOList);
    }
}
