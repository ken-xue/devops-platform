package io.kenxue.devops.application.kubernetes.clusternode.command.query;

import io.kenxue.devops.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodePageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.kubernetes.ClusterNodeRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterNodePageQryExe {

    @Resource
    private ClusterNodeRepository clusterNodeRepository;
    @Resource
    private ClusterNode2DTOAssembler clusterNode2DTOAssembler;

    public PageResponse<ClusterNodeDTO> execute(ClusterNodePageQry qry) {
        Page<ClusterNode> page = clusterNodeRepository.page(qry);
        return PageResponse.of(clusterNode2DTOAssembler.toDTOPage(page));
    }
}
