package io.kenxue.cicd.application.kubernetes.cluster.command;

import io.kenxue.cicd.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
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
