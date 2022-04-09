package io.kenxue.cicd.application.application.pipeline.pipeline.command.query;

import io.kenxue.cicd.application.application.pipeline.pipeline.assembler.ApplicationPipeline2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineListQry;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationPipelineListQryExe {
    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;
    @Resource
    private ApplicationPipeline2DTOAssembler applicationPipeline2DTOAssembler;

    public MultiResponse<ApplicationPipelineDTO> execute(ApplicationPipelineListQry qry) {
        List<ApplicationPipeline> applicationPipelineDTOList = applicationPipelineRepository.list(qry);
        return MultiResponse.of(applicationPipeline2DTOAssembler.toDTOList(applicationPipelineDTOList));
    }
}
