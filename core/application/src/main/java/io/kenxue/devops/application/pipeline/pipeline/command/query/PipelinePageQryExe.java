package io.kenxue.devops.application.pipeline.pipeline.command.query;

import io.kenxue.devops.application.pipeline.pipeline.assembler.Pipeline2DTOAssembler;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineDTO;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelinePageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.pipeline.PipelineRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.pipeline.Pipeline;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class PipelinePageQryExe {

    @Resource
    private PipelineRepository pipelineRepository;
    @Resource
    private Pipeline2DTOAssembler pipeline2DTOAssembler;

    public PageResponse<PipelineDTO> execute(PipelinePageQry qry) {
        Page<Pipeline> page = pipelineRepository.page(qry);
        return PageResponse.of(pipeline2DTOAssembler.toDTOPage(page));
    }
}
