package io.kenxue.cicd.application.pipeline.pipeline.command.query;

import io.kenxue.cicd.application.pipeline.pipeline.assembler.Pipeline2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelinePageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class PipelinePageQryExe {

    @Resource
    private ApplicationPipelineRepository applicationPipelineRepository;
    @Resource
    private Pipeline2DTOAssembler pipeline2DTOAssembler;

    public PageResponse<PipelineDTO> execute(PipelinePageQry qry) {
        Page<Pipeline> page = applicationPipelineRepository.page(qry);
        return PageResponse.of(pipeline2DTOAssembler.toDTOPage(page));
    }
}
