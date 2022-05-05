package io.kenxue.cicd.application.application.pipeline.pipelineexecutelogger.command;

import io.kenxue.cicd.application.application.pipeline.pipelineexecutelogger.assembler.PipelineExecuteLogger2DTOAssembler;
import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerUpdateCmd;
import io.kenxue.cicd.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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