package io.kenxue.cicd.domain.repository.pipeline;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerListQry;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerPageQry;
import io.kenxue.cicd.domain.domain.pipeline.PipelineExecuteLogger;
import java.util.List;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
public interface PipelineExecuteLoggerRepository {
    void create(PipelineExecuteLogger pipelineExecuteLogger);
    void update(PipelineExecuteLogger pipelineExecuteLogger);
    void updateByUuid(PipelineExecuteLogger pipelineExecuteLogger);
    PipelineExecuteLogger getById(Long id);
    List<PipelineExecuteLogger> list(PipelineExecuteLoggerListQry pipelineExecuteLoggerListQry);
    Page<PipelineExecuteLogger> page(PipelineExecuteLoggerPageQry qry);
}
