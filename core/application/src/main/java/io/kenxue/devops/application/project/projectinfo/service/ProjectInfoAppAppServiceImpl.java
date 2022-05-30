package io.kenxue.devops.application.project.projectinfo.service;

import io.kenxue.devops.application.project.projectinfo.command.query.ProjectInfoGetQryExe;
import io.kenxue.devops.application.project.projectinfo.command.query.ProjectInfoListQryExe;
import io.kenxue.devops.application.project.projectinfo.command.query.ProjectInfoPageQryExe;
import io.kenxue.devops.coreclient.api.project.ProjectInfoAppService;
import io.kenxue.devops.coreclient.dto.project.projectinfo.*;
import io.kenxue.devops.application.project.projectinfo.command.ProjectInfoAddCmdExe;
import io.kenxue.devops.application.project.projectinfo.command.ProjectInfoDeleteCmdExe;
import io.kenxue.devops.application.project.projectinfo.command.ProjectInfoUpdateCmdExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Service
public class ProjectInfoAppAppServiceImpl implements ProjectInfoAppService {
    @Resource
    private ProjectInfoAddCmdExe projectInfoAddCmdExe;
    @Resource
    private ProjectInfoUpdateCmdExe projectInfoUpdateCmdExe;
    @Resource
    private ProjectInfoGetQryExe projectInfoGetQryExe;
    @Resource
    private ProjectInfoListQryExe projectInfoListQryExe;
    @Resource
    private ProjectInfoPageQryExe projectInfoPageQryExe;
    @Resource
    private ProjectInfoDeleteCmdExe projectInfoDeleteCmdExe;

    public Response add(ProjectInfoAddCmd projectInfoAddCmd) {
        return  projectInfoAddCmdExe.execute(projectInfoAddCmd);
    }

    public Response update(ProjectInfoUpdateCmd cmd) {
        return projectInfoUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ProjectInfoDTO> getById(ProjectInfoGetQry qry) {
        return projectInfoGetQryExe.execute(qry);
    }

    public MultiResponse<ProjectInfoDTO> list(ProjectInfoListQry qry) {
        return projectInfoListQryExe.execute(qry);
    }

    public Response delete(ProjectInfoDeleteCmd projectInfoDeleteCmd) {
        return projectInfoDeleteCmdExe.execute(projectInfoDeleteCmd);
    }

    public PageResponse<ProjectInfoDTO> page(ProjectInfoPageQry projectInfoPageQry) {
        return projectInfoPageQryExe.execute(projectInfoPageQry);
    }

}
