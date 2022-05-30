package io.kenxue.devops.application.kubernetes.cluster.command.query;

import io.kenxue.devops.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterDTO;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.kubernetes.ClusterRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.kubernetes.Cluster;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
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
