package io.kenxue.devops.application.project.projectinfo.command;

import io.kenxue.devops.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.domain.project.ProjectOfUser;
import io.kenxue.devops.domain.factory.project.ProjectOfUserFactory;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoAddCmd;
import io.kenxue.devops.domain.repository.project.ProjectOfUserRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
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
            of.create(UserThreadContext.get());
            if(!v.equals(projectInfo.getUserUuid()))
            projectOfUserRepository.create(of);
        });
        return Response.success();
    }
}
