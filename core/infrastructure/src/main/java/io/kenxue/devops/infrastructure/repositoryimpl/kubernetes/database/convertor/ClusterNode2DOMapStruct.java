package io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.convertor;

import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterNodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClusterNode2DOMapStruct {

    ClusterNode2DOMapStruct INSTANCE = Mappers.getMapper(ClusterNode2DOMapStruct.class);

    @Mappings({})
    ClusterNodeDO toDO(ClusterNode clusterNode);

    @Mappings({})
    ClusterNode toDomain(ClusterNodeDO clusterNodeDO);

    List<ClusterNodeDO> toDOList(List<ClusterNode> clusterNodeList);

    List<ClusterNode> toDomainList(List<ClusterNodeDO> clusterNodeDOList);
}
