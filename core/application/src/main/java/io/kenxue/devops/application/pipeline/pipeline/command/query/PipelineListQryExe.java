package io.kenxue.devops.application.pipeline.pipeline.command.query;

import io.kenxue.devops.application.pipeline.pipeline.assembler.Pipeline2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineDTO;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineListQry;
import io.kenxue.devops.domain.domain.pipeline.Pipeline;
import io.kenxue.devops.domain.repository.pipeline.PipelineRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class PipelineListQryExe {
    @Resource
    private PipelineRepository pipelineRepository;
    @Resource
    private Pipeline2DTOAssembler pipeline2DTOAssembler;

    public MultiResponse<PipelineDTO> execute(PipelineListQry qry) {
        List<Pipeline> pipelineDTOList = pipelineRepository.list(qry);
        return MultiResponse.of(pipeline2DTOAssembler.toDTOList(pipelineDTOList));
    }
}
