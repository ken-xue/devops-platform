package io.kenxue.devops.application.kubernetes.clusternode.command.query;

import io.kenxue.devops.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeGetQry;
import io.kenxue.devops.domain.repository.kubernetes.ClusterNodeRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterNodeGetQryExe {

    @Resource
    private ClusterNodeRepository clusterNodeRepository;
    @Resource
    private ClusterNode2DTOAssembler clusterNode2DTOAssembler;

    public SingleResponse<ClusterNodeDTO> execute(ClusterNodeGetQry qry) {
        return SingleResponse.of(clusterNode2DTOAssembler.toDTO(clusterNodeRepository.getById(qry.getId())));
    }

}
