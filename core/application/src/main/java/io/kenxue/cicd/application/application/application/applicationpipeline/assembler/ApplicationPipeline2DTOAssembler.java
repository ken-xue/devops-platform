package io.kenxue.cicd.application.application.application.applicationpipeline.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineDTO;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Component
public class ApplicationPipeline2DTOAssembler implements Assembler<ApplicationPipelineDTO, ApplicationPipeline> {

    @Override
    public ApplicationPipelineDTO toDTO(ApplicationPipeline applicationPipeline) {
        return ApplicationPipeline2DTOMapStruct.INSTANCE.toDTO(applicationPipeline);
    }

    @Override
    public ApplicationPipeline toDomain(ApplicationPipelineDTO applicationPipelineDTO) {
        return ApplicationPipeline2DTOMapStruct.INSTANCE.toDomain(applicationPipelineDTO);
    }

    @Override
    public List<ApplicationPipelineDTO> toDTOList(List<ApplicationPipeline> applicationPipelineList) {
        return ApplicationPipeline2DTOMapStruct.INSTANCE.toDTOList(applicationPipelineList);
    }

    @Override
    public List<ApplicationPipeline> toDomainList(List<ApplicationPipelineDTO> applicationPipelineDTOList) {
        return ApplicationPipeline2DTOMapStruct.INSTANCE.toDomainList(applicationPipelineDTOList);
    }

    @Override
    public Page<ApplicationPipelineDTO> toDTOPage(Page<ApplicationPipeline> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), ApplicationPipeline2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
