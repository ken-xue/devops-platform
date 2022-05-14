package io.kenxue.cicd.application.kubernetes.cluster.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterDTO;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
public class Cluster2DTOAssembler implements Assembler<ClusterDTO, Cluster> {

    @Override
    public ClusterDTO toDTO(Cluster cluster) {
        return Cluster2DTOMapStruct.INSTANCE.toDTO(cluster);
    }

    @Override
    public Cluster toDomain(ClusterDTO clusterDTO) {
        return Cluster2DTOMapStruct.INSTANCE.toDomain(clusterDTO);
    }

    @Override
    public List<ClusterDTO> toDTOList(List<Cluster> clusterList) {
        return Cluster2DTOMapStruct.INSTANCE.toDTOList(clusterList);
    }

    @Override
    public List<Cluster> toDomainList(List<ClusterDTO> clusterDTOList) {
        return Cluster2DTOMapStruct.INSTANCE.toDomainList(clusterDTOList);
    }

    @Override
    public Page<ClusterDTO> toDTOPage(Page<Cluster> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Cluster2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
