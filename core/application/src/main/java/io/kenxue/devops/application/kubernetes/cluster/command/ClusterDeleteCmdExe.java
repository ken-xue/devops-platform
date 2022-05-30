package io.kenxue.devops.application.kubernetes.cluster.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.devops.domain.domain.kubernetes.Cluster;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterDeleteCmdExe {
    @Resource
    private ClusterRepository clusterRepository;

    public Response execute(ClusterDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Cluster cluster =clusterRepository.getById(id);
            cluster.deleted(UserThreadContext.get());
            clusterRepository.update(cluster);
        }
        return Response.success();
    }
}
