package io.kenxue.cicd.application.application.application.applicationpipeline.command.query;

import io.kenxue.cicd.application.application.application.applicationpipeline.assembler.ApplicationPipeline2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineDTO;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelinePageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Component
public class ApplicationPipelinePageQryExe {

    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;
    @Resource
    private ApplicationPipeline2DTOAssembler applicationPipeline2DTOAssembler;

    public PageResponse<ApplicationPipelineDTO> execute(ApplicationPipelinePageQry qry) {
        Page<ApplicationPipeline> page = applicationPipelineRepository.page(qry);
        return PageResponse.of(applicationPipeline2DTOAssembler.toDTOPage(page));
    }
}
