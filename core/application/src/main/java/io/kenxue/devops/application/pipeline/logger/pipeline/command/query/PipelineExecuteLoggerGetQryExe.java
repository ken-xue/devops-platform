package io.kenxue.devops.application.pipeline.logger.pipeline.command.query;

import io.kenxue.devops.application.pipeline.logger.pipeline.assembler.PipelineExecuteLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerGetQry;
import io.kenxue.devops.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Component
public class PipelineExecuteLoggerGetQryExe {

    @Resource
    private PipelineExecuteLoggerRepository pipelineExecuteLoggerRepository;
    @Resource
    private PipelineExecuteLogger2DTOAssembler pipelineExecuteLogger2DTOAssembler;

    public SingleResponse<PipelineExecuteLoggerDTO> execute(PipelineExecuteLoggerGetQry qry) {
        return SingleResponse.of(pipelineExecuteLogger2DTOAssembler.toDTO(pipelineExecuteLoggerRepository.getById(qry.getId())));
    }

}
