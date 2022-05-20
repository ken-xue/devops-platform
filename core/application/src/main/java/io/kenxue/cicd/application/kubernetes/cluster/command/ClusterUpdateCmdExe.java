package io.kenxue.cicd.application.kubernetes.cluster.command;

import io.kenxue.cicd.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterUpdateCmd;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterUpdateCmdExe {

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;

    public Response execute(ClusterUpdateCmd cmd) {
        Cluster cluster = cluster2DTOAssembler.toDomain(cmd.getClusterDTO());
        clusterRepository.update(cluster);
        return Response.success();
    }
}