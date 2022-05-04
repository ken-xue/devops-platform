package io.kenxue.cicd.application.project.projectofuser.service;

import io.kenxue.cicd.coreclient.api.project.ProjectOfUserAppService;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.*;
import io.kenxue.cicd.application.project.projectofuser.command.ProjectOfUserAddCmdExe;
import io.kenxue.cicd.application.project.projectofuser.command.ProjectOfUserDeleteCmdExe;
import io.kenxue.cicd.application.project.projectofuser.command.ProjectOfUserUpdateCmdExe;
import io.kenxue.cicd.application.project.projectofuser.command.query.ProjectOfUserGetQryExe;
import io.kenxue.cicd.application.project.projectofuser.command.query.ProjectOfUserListQryExe;
import io.kenxue.cicd.application.project.projectofuser.command.query.ProjectOfUserPageQryExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Service
public class ProjectOfUserAppAppServiceImpl implements ProjectOfUserAppService {
    @Resource
    private ProjectOfUserAddCmdExe projectOfUserAddCmdExe;
    @Resource
    private ProjectOfUserUpdateCmdExe projectOfUserUpdateCmdExe;
    @Resource
    private ProjectOfUserGetQryExe projectOfUserGetQryExe;
    @Resource
    private ProjectOfUserListQryExe projectOfUserListQryExe;
    @Resource
    private ProjectOfUserPageQryExe projectOfUserPageQryExe;
    @Resource
    private ProjectOfUserDeleteCmdExe projectOfUserDeleteCmdExe;

    public Response add(ProjectOfUserAddCmd projectOfUserAddCmd) {
        return  projectOfUserAddCmdExe.execute(projectOfUserAddCmd);
    }

    public Response update(ProjectOfUserUpdateCmd cmd) {
        return projectOfUserUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ProjectOfUserDTO> getById(ProjectOfUserGetQry qry) {
        return projectOfUserGetQryExe.execute(qry);
    }

    public MultiResponse<ProjectOfUserDTO> list(ProjectOfUserListQry qry) {
        return projectOfUserListQryExe.execute(qry);
    }

    public Response delete(ProjectOfUserDeleteCmd projectOfUserDeleteCmd) {
        return projectOfUserDeleteCmdExe.execute(projectOfUserDeleteCmd);
    }

    public PageResponse<ProjectOfUserDTO> page(ProjectOfUserPageQry projectOfUserPageQry) {
        return projectOfUserPageQryExe.execute(projectOfUserPageQry);
    }

}
