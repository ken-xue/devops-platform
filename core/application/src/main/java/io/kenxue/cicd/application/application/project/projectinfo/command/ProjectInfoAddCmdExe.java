package io.kenxue.cicd.application.application.project.projectinfo.command;

import io.kenxue.cicd.application.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import io.kenxue.cicd.domain.factory.project.ProjectOfUserFactory;
import io.kenxue.cicd.domain.repository.project.ProjectInfoRepository;
import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoAddCmd;
import io.kenxue.cicd.domain.repository.project.ProjectOfUserRepository;
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
    @Resource
    private ProjectOfUserRepository projectOfUserRepository;

    public Response execute(ProjectInfoAddCmd cmd) {
        ProjectInfo projectInfo = projectInfo2DTOAssembler.toDomain(cmd.getProjectInfoDTO());
        projectInfo.create(UserThreadContext.get());
        projectInfoRepository.create(projectInfo);
        projectInfo.getUserMembers().forEach(v->{
            ProjectOfUser of = ProjectOfUserFactory.getProjectOfUser();
            of.setProjectUuid(projectInfo.getUuid());
            of.setUserUuid(v);
            projectOfUserRepository.create(of);
        });
        return Response.success();
    }
}
