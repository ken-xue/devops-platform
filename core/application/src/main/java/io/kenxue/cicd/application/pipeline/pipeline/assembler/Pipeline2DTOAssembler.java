package io.kenxue.cicd.application.pipeline.pipeline.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineDTO;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class Pipeline2DTOAssembler implements Assembler<PipelineDTO, Pipeline> {

    @Override
    public PipelineDTO toDTO(Pipeline pipeline) {
        return Pipeline2DTOMapStruct.INSTANCE.toDTO(pipeline);
    }

    @Override
    public Pipeline toDomain(PipelineDTO pipelineDTO) {
        return Pipeline2DTOMapStruct.INSTANCE.toDomain(pipelineDTO);
    }

    @Override
    public List<PipelineDTO> toDTOList(List<Pipeline> pipelineList) {
        return Pipeline2DTOMapStruct.INSTANCE.toDTOList(pipelineList);
    }

    @Override
    public List<Pipeline> toDomainList(List<PipelineDTO> pipelineDTOList) {
        return Pipeline2DTOMapStruct.INSTANCE.toDomainList(pipelineDTOList);
    }

    @Override
    public Page<PipelineDTO> toDTOPage(Page<Pipeline> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Pipeline2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
