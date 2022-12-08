package io.kenxue.devops.application.kubernetes.clusternode.command.query;

import io.kenxue.devops.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeListQry;
import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import io.kenxue.devops.domain.repository.kubernetes.ClusterNodeRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterNodeListQryExe {
    @Resource
    private ClusterNodeRepository clusterNodeRepository;
    @Resource
    private ClusterNode2DTOAssembler clusterNode2DTOAssembler;

    public MultiResponse<ClusterNodeDTO> execute(ClusterNodeListQry qry) {
        List<ClusterNode> clusterNodeDTOList = clusterNodeRepository.list(qry);
        return MultiResponse.of(clusterNode2DTOAssembler.toDTOList(clusterNodeDTOList));
    }
}
