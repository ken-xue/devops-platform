package io.kenxue.devops.application.kubernetes.cluster.command.query;

import io.kenxue.devops.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.devops.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterGetQry;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeListQry;
import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import io.kenxue.devops.domain.repository.kubernetes.ClusterNodeRepository;
import io.kenxue.devops.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.devops.sharedataboject.kubernetes.enums.KubernetesNodeEnum;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterGetQryExe {

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;
    @Resource
    private ClusterNodeRepository clusterNodeRepository;
    @Resource
    private ClusterNode2DTOAssembler clusterNode2DTOAssembler;

    public SingleResponse<ClusterDTO> execute(ClusterGetQry qry) {
        ClusterDTO clusterDTO = cluster2DTOAssembler.toDTO(clusterRepository.getById(qry.getId()));
        List<ClusterNode> list = clusterNodeRepository.list(new ClusterNodeListQry().setClusterUuid(clusterDTO.getUuid()));
        //获取集群节点
        List<ClusterNode> workNodeList = list.stream().filter(v -> KubernetesNodeEnum.WORK.name().equals(v.getType())).collect(Collectors.toList());
        List<ClusterNode> masterNodeList = list.stream().filter(v -> KubernetesNodeEnum.MASTER.name().equals(v.getType())).collect(Collectors.toList());
        clusterDTO.setMasterNodeList(clusterNode2DTOAssembler.toDTOList(masterNodeList));
        clusterDTO.setWorkNodeList(clusterNode2DTOAssembler.toDTOList(workNodeList));
        return SingleResponse.of(clusterDTO);
    }

}
