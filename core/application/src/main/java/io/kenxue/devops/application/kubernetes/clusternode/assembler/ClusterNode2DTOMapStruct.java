package io.kenxue.devops.application.kubernetes.clusternode.assembler;

import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
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
public interface ClusterNode2DTOMapStruct {

    ClusterNode2DTOMapStruct INSTANCE = Mappers.getMapper(ClusterNode2DTOMapStruct.class);

    @Mappings({})
    ClusterNodeDTO toDTO(ClusterNode clusterNode);

    @Mappings({})
    ClusterNode toDomain(ClusterNodeDTO clusterNodeDTO);

    List<ClusterNodeDTO> toDTOList(List<ClusterNode> ClusterNodeList);

    List<ClusterNode> toDomainList(List<ClusterNodeDTO> ClusterNodeDTOList);
}
