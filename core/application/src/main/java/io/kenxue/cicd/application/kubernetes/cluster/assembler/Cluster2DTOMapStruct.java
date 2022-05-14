package io.kenxue.cicd.application.kubernetes.cluster.assembler;

import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterDTO;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
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
public interface Cluster2DTOMapStruct {

    Cluster2DTOMapStruct INSTANCE = Mappers.getMapper(Cluster2DTOMapStruct.class);

    @Mappings({})
    ClusterDTO toDTO(Cluster cluster);

    @Mappings({})
    Cluster toDomain(ClusterDTO clusterDTO);

    List<ClusterDTO> toDTOList(List<Cluster> ClusterList);

    List<Cluster> toDomainList(List<ClusterDTO> ClusterDTOList);
}
