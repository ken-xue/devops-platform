package io.kenxue.devops.application.application.config.service;

import io.kenxue.devops.coreclient.api.application.ApplicationConfigAppService;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.*;
import io.kenxue.devops.application.application.config.command.ApplicationConfigAddCmdExe;
import io.kenxue.devops.application.application.config.command.ApplicationConfigDeleteCmdExe;
import io.kenxue.devops.application.application.config.command.ApplicationConfigUpdateCmdExe;
import io.kenxue.devops.application.application.config.command.query.ApplicationConfigGetQryExe;
import io.kenxue.devops.application.application.config.command.query.ApplicationConfigListQryExe;
import io.kenxue.devops.application.application.config.command.query.ApplicationConfigPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Service
public class ApplicationConfigAppAppServiceImpl implements ApplicationConfigAppService {
    @Resource
    private ApplicationConfigAddCmdExe applicationConfigAddCmdExe;
    @Resource
    private ApplicationConfigUpdateCmdExe applicationConfigUpdateCmdExe;
    @Resource
    private ApplicationConfigGetQryExe applicationConfigGetQryExe;
    @Resource
    private ApplicationConfigListQryExe applicationConfigListQryExe;
    @Resource
    private ApplicationConfigPageQryExe applicationConfigPageQryExe;
    @Resource
    private ApplicationConfigDeleteCmdExe applicationConfigDeleteCmdExe;

    public Response add(ApplicationConfigAddCmd applicationConfigAddCmd) {
        return  applicationConfigAddCmdExe.execute(applicationConfigAddCmd);
    }

    public Response update(ApplicationConfigUpdateCmd cmd) {
        return applicationConfigUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ApplicationConfigDTO> getById(ApplicationConfigGetQry qry) {
        return applicationConfigGetQryExe.execute(qry);
    }

    public MultiResponse<ApplicationConfigDTO> list(ApplicationConfigListQry qry) {
        return applicationConfigListQryExe.execute(qry);
    }

    public Response delete(ApplicationConfigDeleteCmd applicationConfigDeleteCmd) {
        return applicationConfigDeleteCmdExe.execute(applicationConfigDeleteCmd);
    }

    public PageResponse<ApplicationConfigDTO> page(ApplicationConfigPageQry applicationConfigPageQry) {
        return applicationConfigPageQryExe.execute(applicationConfigPageQry);
    }

}
