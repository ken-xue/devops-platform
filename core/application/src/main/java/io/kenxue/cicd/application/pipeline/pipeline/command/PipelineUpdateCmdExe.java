package io.kenxue.cicd.application.pipeline.pipeline.command;

import io.kenxue.cicd.application.pipeline.pipeline.assembler.Pipeline2DTOAssembler;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineUpdateCmd;
import io.kenxue.cicd.domain.repository.pipeline.PipelineRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class PipelineUpdateCmdExe {

    @Resource
    private PipelineRepository pipelineRepository;
    @Resource
    private Pipeline2DTOAssembler pipeline2DTOAssembler;

    public Response execute(PipelineUpdateCmd cmd) {
        Pipeline pipeline = pipeline2DTOAssembler.toDomain(cmd.getPipelineDTO());
        pipeline.serializable();
        pipeline.validate();
        pipelineRepository.update(pipeline);
        return Response.success();
    }
}