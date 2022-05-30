package io.kenxue.devops.application.middleware.jenkins.service;

import io.kenxue.devops.application.middleware.jenkins.command.JenkinsAddCmdExe;
import io.kenxue.devops.application.middleware.jenkins.command.JenkinsDeleteCmdExe;
import io.kenxue.devops.application.middleware.jenkins.command.JenkinsUpdateCmdExe;
import io.kenxue.devops.application.middleware.jenkins.command.query.JenkinsGetQryExe;
import io.kenxue.devops.application.middleware.jenkins.command.query.JenkinsPageQryExe;
import io.kenxue.devops.coreclient.api.middleware.JenkinsAppService;
import io.kenxue.devops.application.middleware.jenkins.command.query.JenkinsListQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Service
public class JenkinsAppAppServiceImpl implements JenkinsAppService {
    @Resource
    private JenkinsAddCmdExe jenkinsAddCmdExe;
    @Resource
    private JenkinsUpdateCmdExe jenkinsUpdateCmdExe;
    @Resource
    private JenkinsGetQryExe jenkinsGetQryExe;
    @Resource
    private JenkinsListQryExe jenkinsListQryExe;
    @Resource
    private JenkinsPageQryExe jenkinsPageQryExe;
    @Resource
    private JenkinsDeleteCmdExe jenkinsDeleteCmdExe;

    public Response add(JenkinsAddCmd jenkinsAddCmd) {
        return  jenkinsAddCmdExe.execute(jenkinsAddCmd);
    }

    public Response update(JenkinsUpdateCmd cmd) {
        return jenkinsUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<JenkinsDTO> getById(JenkinsGetQry qry) {
        return jenkinsGetQryExe.execute(qry);
    }

    public MultiResponse<JenkinsDTO> list(JenkinsListQry qry) {
        return jenkinsListQryExe.execute(qry);
    }

    public Response delete(JenkinsDeleteCmd jenkinsDeleteCmd) {
        return jenkinsDeleteCmdExe.execute(jenkinsDeleteCmd);
    }

    public PageResponse<JenkinsDTO> page(JenkinsPageQry jenkinsPageQry) {
        return jenkinsPageQryExe.execute(jenkinsPageQry);
    }

}
