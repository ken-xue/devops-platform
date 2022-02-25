package io.kenxue.cicd.application.application.application.pipelinenode.command.query;

import io.kenxue.cicd.application.application.application.pipelinenode.assembler.PipelineNode2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.application.pipelinenode.PipelineNodeDTO;
import io.kenxue.cicd.coreclient.dto.application.pipelinenode.PipelineNodeListQry;
import io.kenxue.cicd.domain.domain.application.PipelineNode;
import io.kenxue.cicd.domain.repository.application.PipelineNodeRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Component
public class PipelineNodeListQryExe {
    @Resource
    private PipelineNodeRepository pipelineNodeRepository;
    @Resource
    private PipelineNode2DTOAssembler pipelineNode2DTOAssembler;

    public MultiResponse<PipelineNodeDTO> execute(PipelineNodeListQry qry) {
        List<PipelineNode> pipelineNodeDTOList = pipelineNodeRepository.list(qry);
        List<PipelineNodeDTO> ret = pipelineNode2DTOAssembler.toDTOList(pipelineNodeDTOList);
        //构建递归树
        List<PipelineNodeDTO> rootNode = getRootNode(ret);
        rootNode.forEach(v-> setChildren(v,ret));
        return MultiResponse.of(rootNode);
    }

    private void setChildren(PipelineNodeDTO node, List<PipelineNodeDTO> pipelineNodeDTOList) {
        List<PipelineNodeDTO> childList = new ArrayList<>();
        pipelineNodeDTOList.forEach(v->{
            if (node.getUuid().equals(v.getParentUuid()))childList.add(v.setChildren(null));
        });
        node.setChildren(childList);
    }

    private List<PipelineNodeDTO> getRootNode(List<PipelineNodeDTO> pipelineNodeDTOList) {
        List<PipelineNodeDTO> ret = new ArrayList<>();
        for (PipelineNodeDTO node:pipelineNodeDTOList){
            if (node.getType()==0)ret.add(node);
        }
        return ret;
    }
}
