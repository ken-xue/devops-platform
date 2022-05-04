package io.kenxue.cicd.application.application.appofuser.service;

import io.kenxue.cicd.coreclient.api.application.ApplicationOfUserAppService;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.*;
import io.kenxue.cicd.application.application.appofuser.command.ApplicationOfUserAddCmdExe;
import io.kenxue.cicd.application.application.appofuser.command.ApplicationOfUserDeleteCmdExe;
import io.kenxue.cicd.application.application.appofuser.command.ApplicationOfUserUpdateCmdExe;
import io.kenxue.cicd.application.application.appofuser.command.query.ApplicationOfUserGetQryExe;
import io.kenxue.cicd.application.application.appofuser.command.query.ApplicationOfUserListQryExe;
import io.kenxue.cicd.application.application.appofuser.command.query.ApplicationOfUserPageQryExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Service
public class ApplicationOfUserAppAppServiceImpl implements ApplicationOfUserAppService {
    @Resource
    private ApplicationOfUserAddCmdExe applicationOfUserAddCmdExe;
    @Resource
    private ApplicationOfUserUpdateCmdExe applicationOfUserUpdateCmdExe;
    @Resource
    private ApplicationOfUserGetQryExe applicationOfUserGetQryExe;
    @Resource
    private ApplicationOfUserListQryExe applicationOfUserListQryExe;
    @Resource
    private ApplicationOfUserPageQryExe applicationOfUserPageQryExe;
    @Resource
    private ApplicationOfUserDeleteCmdExe applicationOfUserDeleteCmdExe;

    public Response add(ApplicationOfUserAddCmd applicationOfUserAddCmd) {
        return  applicationOfUserAddCmdExe.execute(applicationOfUserAddCmd);
    }

    public Response update(ApplicationOfUserUpdateCmd cmd) {
        return applicationOfUserUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ApplicationOfUserDTO> getById(ApplicationOfUserGetQry qry) {
        return applicationOfUserGetQryExe.execute(qry);
    }

    public MultiResponse<ApplicationOfUserDTO> list(ApplicationOfUserListQry qry) {
        return applicationOfUserListQryExe.execute(qry);
    }

    public Response delete(ApplicationOfUserDeleteCmd applicationOfUserDeleteCmd) {
        return applicationOfUserDeleteCmdExe.execute(applicationOfUserDeleteCmd);
    }

    public PageResponse<ApplicationOfUserDTO> page(ApplicationOfUserPageQry applicationOfUserPageQry) {
        return applicationOfUserPageQryExe.execute(applicationOfUserPageQry);
    }

}
