package io.kenxue.devops.application.kubernetes.cluster.command;

import io.kenxue.devops.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.devops.domain.domain.kubernetes.Cluster;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterAddCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
public class ClusterAddCmdExe {

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;

    public Response execute(ClusterAddCmd cmd) {
        Cluster cluster = cluster2DTOAssembler.toDomain(cmd.getClusterDTO());
        cluster.create(UserThreadContext.get());
        clusterRepository.create(cluster);
        return Response.success();
    }
}
