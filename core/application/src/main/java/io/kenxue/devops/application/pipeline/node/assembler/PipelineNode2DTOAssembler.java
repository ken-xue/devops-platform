package io.kenxue.devops.application.pipeline.node.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeDTO;
import io.kenxue.devops.domain.domain.pipeline.PipelineNode;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Component
public class PipelineNode2DTOAssembler implements Assembler<PipelineNodeDTO, PipelineNode> {

    @Override
    public PipelineNodeDTO toDTO(PipelineNode pipelineNode) {
        return PipelineNode2DTOMapStruct.INSTANCE.toDTO(pipelineNode);
    }

    @Override
    public PipelineNode toDomain(PipelineNodeDTO pipelineNodeDTO) {
        return PipelineNode2DTOMapStruct.INSTANCE.toDomain(pipelineNodeDTO);
    }

    @Override
    public List<PipelineNodeDTO> toDTOList(List<PipelineNode> pipelineNodeList) {
        return PipelineNode2DTOMapStruct.INSTANCE.toDTOList(pipelineNodeList);
    }

    @Override
    public List<PipelineNode> toDomainList(List<PipelineNodeDTO> pipelineNodeDTOList) {
        return PipelineNode2DTOMapStruct.INSTANCE.toDomainList(pipelineNodeDTOList);
    }

    @Override
    public Page<PipelineNodeDTO> toDTOPage(Page<PipelineNode> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), PipelineNode2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
