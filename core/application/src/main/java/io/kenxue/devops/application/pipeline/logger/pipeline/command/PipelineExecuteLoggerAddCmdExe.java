package io.kenxue.devops.application.pipeline.logger.pipeline.command;

import io.kenxue.devops.application.pipeline.logger.pipeline.assembler.PipelineExecuteLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerAddCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Component
public class PipelineExecuteLoggerAddCmdExe {

    @Resource
    private PipelineExecuteLoggerRepository pipelineExecuteLoggerRepository;
    @Resource
    private PipelineExecuteLogger2DTOAssembler pipelineExecuteLogger2DTOAssembler;

    public Response execute(PipelineExecuteLoggerAddCmd cmd) {
        PipelineExecuteLogger pipelineExecuteLogger = pipelineExecuteLogger2DTOAssembler.toDomain(cmd.getPipelineExecuteLoggerDTO());
        pipelineExecuteLogger.create(UserThreadContext.get());
        pipelineExecuteLoggerRepository.create(pipelineExecuteLogger);
        return Response.success();
    }
}
