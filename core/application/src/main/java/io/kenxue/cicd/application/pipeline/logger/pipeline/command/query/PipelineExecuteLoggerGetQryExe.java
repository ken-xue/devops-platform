package io.kenxue.cicd.application.pipeline.logger.pipeline.command.query;

import io.kenxue.cicd.application.pipeline.logger.pipeline.assembler.PipelineExecuteLogger2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerGetQry;
import io.kenxue.cicd.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
