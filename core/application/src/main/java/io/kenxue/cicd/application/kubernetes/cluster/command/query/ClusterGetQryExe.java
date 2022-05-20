package io.kenxue.cicd.application.kubernetes.cluster.command.query;

import io.kenxue.cicd.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterGetQry;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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

    public SingleResponse<ClusterDTO> execute(ClusterGetQry qry) {
        return SingleResponse.of(cluster2DTOAssembler.toDTO(clusterRepository.getById(qry.getId())));
    }

}
