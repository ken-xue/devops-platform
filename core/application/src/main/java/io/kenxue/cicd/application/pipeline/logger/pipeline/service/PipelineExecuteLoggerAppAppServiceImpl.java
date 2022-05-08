package io.kenxue.cicd.application.pipeline.logger.pipeline.service;

import io.kenxue.cicd.coreclient.api.pipeline.PipelineExecuteLoggerAppService;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelineexecutelogger.*;
import io.kenxue.cicd.application.pipeline.logger.pipeline.command.PipelineExecuteLoggerAddCmdExe;
import io.kenxue.cicd.application.pipeline.logger.pipeline.command.PipelineExecuteLoggerDeleteCmdExe;
import io.kenxue.cicd.application.pipeline.logger.pipeline.command.PipelineExecuteLoggerUpdateCmdExe;
import io.kenxue.cicd.application.pipeline.logger.pipeline.command.query.PipelineExecuteLoggerGetQryExe;
import io.kenxue.cicd.application.pipeline.logger.pipeline.command.query.PipelineExecuteLoggerListQryExe;
import io.kenxue.cicd.application.pipeline.logger.pipeline.command.query.PipelineExecuteLoggerPageQryExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Service
public class PipelineExecuteLoggerAppAppServiceImpl implements PipelineExecuteLoggerAppService {
    @Resource
    private PipelineExecuteLoggerAddCmdExe pipelineExecuteLoggerAddCmdExe;
    @Resource
    private PipelineExecuteLoggerUpdateCmdExe pipelineExecuteLoggerUpdateCmdExe;
    @Resource
    private PipelineExecuteLoggerGetQryExe pipelineExecuteLoggerGetQryExe;
    @Resource
    private PipelineExecuteLoggerListQryExe pipelineExecuteLoggerListQryExe;
    @Resource
    private PipelineExecuteLoggerPageQryExe pipelineExecuteLoggerPageQryExe;
    @Resource
    private PipelineExecuteLoggerDeleteCmdExe pipelineExecuteLoggerDeleteCmdExe;

    public Response add(PipelineExecuteLoggerAddCmd pipelineExecuteLoggerAddCmd) {
        return  pipelineExecuteLoggerAddCmdExe.execute(pipelineExecuteLoggerAddCmd);
    }

    public Response update(PipelineExecuteLoggerUpdateCmd cmd) {
        return pipelineExecuteLoggerUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<PipelineExecuteLoggerDTO> getById(PipelineExecuteLoggerGetQry qry) {
        return pipelineExecuteLoggerGetQryExe.execute(qry);
    }

    public MultiResponse<PipelineExecuteLoggerDTO> list(PipelineExecuteLoggerListQry qry) {
        return pipelineExecuteLoggerListQryExe.execute(qry);
    }

    public Response delete(PipelineExecuteLoggerDeleteCmd pipelineExecuteLoggerDeleteCmd) {
        return pipelineExecuteLoggerDeleteCmdExe.execute(pipelineExecuteLoggerDeleteCmd);
    }

    public PageResponse<PipelineExecuteLoggerDTO> page(PipelineExecuteLoggerPageQry pipelineExecuteLoggerPageQry) {
        return pipelineExecuteLoggerPageQryExe.execute(pipelineExecuteLoggerPageQry);
    }

}
