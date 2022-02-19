package io.kenxue.cicd.application.application.project.projectinfo.command;

import io.kenxue.cicd.application.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.project.ProjectInfoRepository;
import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectInfoAddCmdExe {

    @Resource
    private ProjectInfoRepository projectInfoRepository;
    @Resource
    private ProjectInfo2DTOAssembler projectInfo2DTOAssembler;

    public Response execute(ProjectInfoAddCmd cmd) {
        ProjectInfo projectInfo = projectInfo2DTOAssembler.toDomain(cmd.getProjectInfoDTO());
        projectInfo.create(UserThreadContext.get());
        projectInfoRepository.create(projectInfo);
        return Response.success();
    }
}
