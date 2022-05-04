package io.kenxue.cicd.application.pipeline.pipeline.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineDTO;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationPipeline2DTOAssembler implements Assembler<ApplicationPipelineDTO, Pipeline> {

    @Override
    public ApplicationPipelineDTO toDTO(Pipeline pipeline) {
        return ApplicationPipeline2DTOMapStruct.INSTANCE.toDTO(pipeline);
    }

    @Override
    public Pipeline toDomain(ApplicationPipelineDTO applicationPipelineDTO) {
        return ApplicationPipeline2DTOMapStruct.INSTANCE.toDomain(applicationPipelineDTO);
    }

    @Override
    public List<ApplicationPipelineDTO> toDTOList(List<Pipeline> pipelineList) {
        return ApplicationPipeline2DTOMapStruct.INSTANCE.toDTOList(pipelineList);
    }

    @Override
    public List<Pipeline> toDomainList(List<ApplicationPipelineDTO> applicationPipelineDTOList) {
        return ApplicationPipeline2DTOMapStruct.INSTANCE.toDomainList(applicationPipelineDTOList);
    }

    @Override
    public Page<ApplicationPipelineDTO> toDTOPage(Page<Pipeline> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), ApplicationPipeline2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
