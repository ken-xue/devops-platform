package io.kenxue.cicd.application.application.application.applicationpipeline.command.query;

import io.kenxue.cicd.application.application.application.applicationpipeline.assembler.ApplicationPipeline2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineGetQry;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Component
public class ApplicationPipelineGetQryExe {

    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;
    @Resource
    private ApplicationPipeline2DTOAssembler applicationPipeline2DTOAssembler;

    public SingleResponse<ApplicationPipelineDTO> execute(ApplicationPipelineGetQry qry) {
        return SingleResponse.of(applicationPipeline2DTOAssembler.toDTO(applicationPipelineRepository.getById(qry.getId())));
    }

}
