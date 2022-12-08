package io.kenxue.devops.application.pipeline.logger.pipeline.command;

import io.kenxue.devops.application.pipeline.logger.pipeline.assembler.PipelineExecuteLogger2DTOAssembler;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerUpdateCmd;
import io.kenxue.devops.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Component
public class PipelineExecuteLoggerUpdateCmdExe {

    @Resource
    private PipelineExecuteLoggerRepository pipelineExecuteLoggerRepository;
    @Resource
    private PipelineExecuteLogger2DTOAssembler pipelineExecuteLogger2DTOAssembler;

    public Response execute(PipelineExecuteLoggerUpdateCmd cmd) {
        PipelineExecuteLogger pipelineExecuteLogger = pipelineExecuteLogger2DTOAssembler.toDomain(cmd.getPipelineExecuteLoggerDTO());
        pipelineExecuteLoggerRepository.update(pipelineExecuteLogger);
        return Response.success();
    }
}