package io.kenxue.cicd.application.kubernetes.clusternode.command.query;

import io.kenxue.cicd.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.ClusterNodeGetQry;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterNodeRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
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
