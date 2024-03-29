package io.kenxue.devops.application.pipeline.logger.pipeline.command.query;

import io.kenxue.devops.application.pipeline.logger.pipeline.assembler.PipelineExecuteLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerDTO;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerListQry;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Component
public class PipelineExecuteLoggerListQryExe {
    @Resource
    private PipelineExecuteLoggerRepository pipelineExecuteLoggerRepository;
    @Resource
    private PipelineExecuteLogger2DTOAssembler pipelineExecuteLogger2DTOAssembler;

    public MultiResponse<PipelineExecuteLoggerDTO> execute(PipelineExecuteLoggerListQry qry) {
        List<PipelineExecuteLogger> pipelineExecuteLoggerDTOList = pipelineExecuteLoggerRepository.list(qry);
        return MultiResponse.of(pipelineExecuteLogger2DTOAssembler.toDTOList(pipelineExecuteLoggerDTOList));
    }
}
