package io.kenxue.devops.application.kubernetes.clusternode.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.kubernetes.ClusterNodeRepository;
import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterNodeDeleteCmdExe {
    @Resource
    private ClusterNodeRepository clusterNodeRepository;

    public Response execute(ClusterNodeDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            ClusterNode clusterNode =clusterNodeRepository.getById(id);
            clusterNode.deleted(UserThreadContext.get());
            clusterNodeRepository.update(clusterNode);
        }
        return Response.success();
    }
}
