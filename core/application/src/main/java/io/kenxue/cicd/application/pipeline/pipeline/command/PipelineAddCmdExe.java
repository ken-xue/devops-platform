package io.kenxue.cicd.application.pipeline.pipeline.command;

import io.kenxue.cicd.application.pipeline.pipeline.assembler.Pipeline2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;
import io.kenxue.cicd.domain.repository.pipeline.PipelineRepository;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PipelineAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class PipelineAddCmdExe {

    @Resource
    private PipelineRepository pipelineRepository;
    @Resource
    private Pipeline2DTOAssembler pipeline2DTOAssembler;

    public Response execute(PipelineAddCmd cmd) {
        Pipeline pipeline = pipeline2DTOAssembler.toDomain(cmd.getPipelineDTO());
        pipeline.validate();
        pipeline.create(UserThreadContext.get());
        //编译流程图
        pipeline.serializable();
        pipelineRepository.create(pipeline);
        Long id = pipelineRepository.getByUUID(pipeline.getUuid()).getId();
        return SingleResponse.of(id);
    }
}
