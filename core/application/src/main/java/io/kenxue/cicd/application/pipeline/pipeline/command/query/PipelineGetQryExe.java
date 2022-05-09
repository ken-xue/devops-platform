package io.kenxue.cicd.application.pipeline.pipeline.command.query;

import io.kenxue.cicd.application.pipeline.pipeline.assembler.Pipeline2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineGetQry;
import io.kenxue.cicd.domain.repository.pipeline.PipelineRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class PipelineGetQryExe {

    @Resource
    private PipelineRepository pipelineRepository;
    @Resource
    private Pipeline2DTOAssembler pipeline2DTOAssembler;

    public SingleResponse<PipelineDTO> execute(PipelineGetQry qry) {
        return SingleResponse.of(pipeline2DTOAssembler.toDTO(pipelineRepository.getById(qry.getId())));
    }

}
