package io.kenxue.cicd.application.pipeline.nodeinfo.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoDTO;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Component
public class PipelineNodeInfo2DTOAssembler implements Assembler<PipelineNodeInfoDTO, PipelineNodeInfo> {

    @Override
    public PipelineNodeInfoDTO toDTO(PipelineNodeInfo pipelineNodeInfo) {
        return PipelineNodeInfo2DTOMapStruct.INSTANCE.toDTO(pipelineNodeInfo);
    }

    @Override
    public PipelineNodeInfo toDomain(PipelineNodeInfoDTO pipelineNodeInfoDTO) {
        return PipelineNodeInfo2DTOMapStruct.INSTANCE.toDomain(pipelineNodeInfoDTO);
    }

    @Override
    public List<PipelineNodeInfoDTO> toDTOList(List<PipelineNodeInfo> pipelineNodeInfoList) {
        return PipelineNodeInfo2DTOMapStruct.INSTANCE.toDTOList(pipelineNodeInfoList);
    }

    @Override
    public List<PipelineNodeInfo> toDomainList(List<PipelineNodeInfoDTO> pipelineNodeInfoDTOList) {
        return PipelineNodeInfo2DTOMapStruct.INSTANCE.toDomainList(pipelineNodeInfoDTOList);
    }

    @Override
    public Page<PipelineNodeInfoDTO> toDTOPage(Page<PipelineNodeInfo> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), PipelineNodeInfo2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
