package io.kenxue.cicd.application.kubernetes.cluster.command.query;

import io.kenxue.cicd.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterDTO;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterPageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
public class ClusterPageQryExe {

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;

    public PageResponse<ClusterDTO> execute(ClusterPageQry qry) {
        Page<Cluster> page = clusterRepository.page(qry);
        return PageResponse.of(cluster2DTOAssembler.toDTOPage(page));
    }
}
