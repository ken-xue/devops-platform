package io.kenxue.cicd.application.kubernetes.clusternode.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import io.kenxue.cicd.domain.domain.kubernetes.ClusterNode;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterNode2DTOAssembler implements Assembler<ClusterNodeDTO, ClusterNode> {

    @Override
    public ClusterNodeDTO toDTO(ClusterNode clusterNode) {
        return ClusterNode2DTOMapStruct.INSTANCE.toDTO(clusterNode);
    }

    @Override
    public ClusterNode toDomain(ClusterNodeDTO clusterNodeDTO) {
        return ClusterNode2DTOMapStruct.INSTANCE.toDomain(clusterNodeDTO);
    }

    @Override
    public List<ClusterNodeDTO> toDTOList(List<ClusterNode> clusterNodeList) {
        return ClusterNode2DTOMapStruct.INSTANCE.toDTOList(clusterNodeList);
    }

    @Override
    public List<ClusterNode> toDomainList(List<ClusterNodeDTO> clusterNodeDTOList) {
        return ClusterNode2DTOMapStruct.INSTANCE.toDomainList(clusterNodeDTOList);
    }

    @Override
    public Page<ClusterNodeDTO> toDTOPage(Page<ClusterNode> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), ClusterNode2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
