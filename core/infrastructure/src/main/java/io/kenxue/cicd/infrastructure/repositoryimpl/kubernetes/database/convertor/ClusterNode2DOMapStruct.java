package io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.convertor;

import io.kenxue.cicd.domain.domain.kubernetes.ClusterNode;
import io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterNodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
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
