package io.kenxue.cicd.application.pipeline.logger.pipeline.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Component
public class PipelineExecuteLoggerDeleteCmdExe {
    @Resource
    private PipelineExecuteLoggerRepository pipelineExecuteLoggerRepository;

    public Response execute(PipelineExecuteLoggerDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            PipelineExecuteLogger pipelineExecuteLogger =pipelineExecuteLoggerRepository.getById(id);
            pipelineExecuteLogger.deleted(UserThreadContext.get());
            pipelineExecuteLoggerRepository.update(pipelineExecuteLogger);
        }
        return Response.success();
    }
}
