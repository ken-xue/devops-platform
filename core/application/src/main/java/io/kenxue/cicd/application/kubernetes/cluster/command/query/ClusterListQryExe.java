package io.kenxue.cicd.application.kubernetes.cluster.command.query;

import io.kenxue.cicd.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterDTO;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterListQry;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
public class ClusterListQryExe {
    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;

    public MultiResponse<ClusterDTO> execute(ClusterListQry qry) {
        List<Cluster> clusterDTOList = clusterRepository.list(qry);
        return MultiResponse.of(cluster2DTOAssembler.toDTOList(clusterDTOList));
    }
}
