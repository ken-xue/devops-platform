package io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.convertor;

import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Cluster2DOMapStruct {

    Cluster2DOMapStruct INSTANCE = Mappers.getMapper(Cluster2DOMapStruct.class);

    @Mappings({})
    ClusterDO toDO(Cluster cluster);

    @Mappings({})
    Cluster toDomain(ClusterDO clusterDO);

    List<ClusterDO> toDOList(List<Cluster> clusterList);

    List<Cluster> toDomainList(List<ClusterDO> clusterDOList);
}
