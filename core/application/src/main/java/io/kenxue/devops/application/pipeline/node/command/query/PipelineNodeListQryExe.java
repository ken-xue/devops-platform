package io.kenxue.devops.application.pipeline.node.command.query;

import io.kenxue.devops.application.pipeline.node.assembler.PipelineNode2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeDTO;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeListQry;
import io.kenxue.devops.domain.domain.pipeline.PipelineNode;
import io.kenxue.devops.domain.repository.application.PipelineNodeRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
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
