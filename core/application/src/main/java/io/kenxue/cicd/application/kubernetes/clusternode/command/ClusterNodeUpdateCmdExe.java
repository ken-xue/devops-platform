package io.kenxue.cicd.application.kubernetes.clusternode.command;

import io.kenxue.cicd.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.cicd.domain.domain.kubernetes.ClusterNode;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.ClusterNodeUpdateCmd;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterNodeRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterNodeUpdateCmdExe {

    @Resource
    private ClusterNodeRepository clusterNodeRepository;
    @Resource
    private ClusterNode2DTOAssembler clusterNode2DTOAssembler;

    public Response execute(ClusterNodeUpdateCmd cmd) {
        ClusterNode clusterNode = clusterNode2DTOAssembler.toDomain(cmd.getClusterNodeDTO());
        clusterNodeRepository.update(clusterNode);
        return Response.success();
    }
}