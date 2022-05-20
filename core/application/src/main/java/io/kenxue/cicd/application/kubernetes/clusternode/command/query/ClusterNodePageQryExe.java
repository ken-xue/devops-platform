package io.kenxue.cicd.application.kubernetes.clusternode.command.query;

import io.kenxue.cicd.application.kubernetes.clusternode.assembler.ClusterNode2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.ClusterNodePageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterNodeRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.kubernetes.ClusterNode;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
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
