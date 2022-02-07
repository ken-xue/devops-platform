package io.kenxue.cicd.application.application.application.applicationpipeline.service;

import io.kenxue.cicd.coreclient.api.application.ApplicationPipelineAppService;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.*;
import io.kenxue.cicd.application.application.application.applicationpipeline.command.ApplicationPipelineAddCmdExe;
import io.kenxue.cicd.application.application.application.applicationpipeline.command.ApplicationPipelineDeleteCmdExe;
import io.kenxue.cicd.application.application.application.applicationpipeline.command.ApplicationPipelineUpdateCmdExe;
import io.kenxue.cicd.application.application.application.applicationpipeline.command.query.ApplicationPipelineGetQryExe;
import io.kenxue.cicd.application.application.application.applicationpipeline.command.query.ApplicationPipelineListQryExe;
import io.kenxue.cicd.application.application.application.applicationpipeline.command.query.ApplicationPipelinePageQryExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Service
public class ApplicationPipelineAppAppServiceImpl implements ApplicationPipelineAppService {
    @Resource
    private ApplicationPipelineAddCmdExe applicationPipelineAddCmdExe;
    @Resource
    private ApplicationPipelineUpdateCmdExe applicationPipelineUpdateCmdExe;
    @Resource
    private ApplicationPipelineGetQryExe applicationPipelineGetQryExe;
    @Resource
    private ApplicationPipelineListQryExe applicationPipelineListQryExe;
    @Resource
    private ApplicationPipelinePageQryExe applicationPipelinePageQryExe;
    @Resource
    private ApplicationPipelineDeleteCmdExe applicationPipelineDeleteCmdExe;

    public Response add(ApplicationPipelineAddCmd applicationPipelineAddCmd) {
        return  applicationPipelineAddCmdExe.execute(applicationPipelineAddCmd);
    }

    public Response update(ApplicationPipelineUpdateCmd cmd) {
        return applicationPipelineUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ApplicationPipelineDTO> getById(ApplicationPipelineGetQry qry) {
        return applicationPipelineGetQryExe.execute(qry);
    }

    public MultiResponse<ApplicationPipelineDTO> list(ApplicationPipelineListQry qry) {
        return applicationPipelineListQryExe.execute(qry);
    }

    public Response delete(ApplicationPipelineDeleteCmd applicationPipelineDeleteCmd) {
        return applicationPipelineDeleteCmdExe.execute(applicationPipelineDeleteCmd);
    }

    public PageResponse<ApplicationPipelineDTO> page(ApplicationPipelinePageQry applicationPipelinePageQry) {
        return applicationPipelinePageQryExe.execute(applicationPipelinePageQry);
    }

}