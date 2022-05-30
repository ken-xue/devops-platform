package io.kenxue.devops.application.kubernetes.clusternode.command;

import io.kenxue.devops.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.kubernetes.ClusterNodeRepository;
import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterNodeAddCmdExe {

    @Resource
    private ClusterNodeRepository clusterNodeRepository;
    @Resource
    private ClusterNode2DTOAssembler clusterNode2DTOAssembler;

    public Response execute(ClusterNodeAddCmd cmd) {
        ClusterNode clusterNode = clusterNode2DTOAssembler.toDomain(cmd.getClusterNodeDTO());
        clusterNode.create(UserThreadContext.get());
        clusterNodeRepository.create(clusterNode);
        return Response.success();
    }
}
