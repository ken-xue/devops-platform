package io.kenxue.cicd.application.application.pipeline.pipelineexecutelogger.command.query;

import io.kenxue.cicd.application.application.pipeline.pipelineexecutelogger.assembler.PipelineExecuteLogger2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerPageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Component
public class PipelineExecuteLoggerPageQryExe {

    @Resource
    private PipelineExecuteLoggerRepository pipelineExecuteLoggerRepository;
    @Resource
    private PipelineExecuteLogger2DTOAssembler pipelineExecuteLogger2DTOAssembler;

    public PageResponse<PipelineExecuteLoggerDTO> execute(PipelineExecuteLoggerPageQry qry) {
        Page<PipelineExecuteLogger> page = pipelineExecuteLoggerRepository.page(qry);
        return PageResponse.of(pipelineExecuteLogger2DTOAssembler.toDTOPage(page));
    }
}
