package io.kenxue.devops.application.application.info.service;

import io.kenxue.devops.application.application.info.command.ApplicationInfoUpdateCmdExe;
import io.kenxue.devops.coreclient.api.application.ApplicationInfoAppService;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.*;
import io.kenxue.devops.application.application.info.command.ApplicationInfoAddCmdExe;
import io.kenxue.devops.application.application.info.command.ApplicationInfoDeleteCmdExe;
import io.kenxue.devops.application.application.info.command.query.ApplicationInfoGetQryExe;
import io.kenxue.devops.application.application.info.command.query.ApplicationInfoListQryExe;
import io.kenxue.devops.application.application.info.command.query.ApplicationInfoPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Service
public class ApplicationInfoAppAppServiceImpl implements ApplicationInfoAppService {
    @Resource
    private ApplicationInfoAddCmdExe applicationInfoAddCmdExe;
    @Resource
    private ApplicationInfoUpdateCmdExe applicationInfoUpdateCmdExe;
    @Resource
    private ApplicationInfoGetQryExe applicationInfoGetQryExe;
    @Resource
    private ApplicationInfoListQryExe applicationInfoListQryExe;
    @Resource
    private ApplicationInfoPageQryExe applicationInfoPageQryExe;
    @Resource
    private ApplicationInfoDeleteCmdExe applicationInfoDeleteCmdExe;

    public Response add(ApplicationInfoAddCmd applicationInfoAddCmd) {
        return  applicationInfoAddCmdExe.execute(applicationInfoAddCmd);
    }

    public Response update(ApplicationInfoUpdateCmd cmd) {
        return applicationInfoUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ApplicationInfoDTO> getById(ApplicationInfoGetQry qry) {
        return applicationInfoGetQryExe.execute(qry);
    }

    public MultiResponse<ApplicationInfoDTO> list(ApplicationInfoListQry qry) {
        return applicationInfoListQryExe.execute(qry);
    }

    public Response delete(ApplicationInfoDeleteCmd applicationInfoDeleteCmd) {
        return applicationInfoDeleteCmdExe.execute(applicationInfoDeleteCmd);
    }

    public PageResponse<ApplicationInfoDTO> page(ApplicationInfoPageQry applicationInfoPageQry) {
        return applicationInfoPageQryExe.execute(applicationInfoPageQry);
    }

}
