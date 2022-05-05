package io.kenxue.cicd.application.application.pipeline.pipelineexecutelogger.command.query;

import io.kenxue.cicd.application.application.pipeline.pipelineexecutelogger.assembler.PipelineExecuteLogger2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerListQry;
import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.cicd.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
