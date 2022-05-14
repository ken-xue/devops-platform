package io.kenxue.cicd.application.kubernetes.clusternode.command.query;

import io.kenxue.cicd.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.ClusterNodeListQry;
import io.kenxue.cicd.domain.domain.kubernetes.ClusterNode;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterNodeRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
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
