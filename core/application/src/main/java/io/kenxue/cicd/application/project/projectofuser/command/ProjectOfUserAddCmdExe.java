package io.kenxue.cicd.application.project.projectofuser.command;

import io.kenxue.cicd.application.project.projectofuser.assembler.ProjectOfUser2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.project.ProjectOfUserRepository;
import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectOfUserAddCmdExe {

    @Resource
    private ProjectOfUserRepository projectOfUserRepository;
    @Resource
    private ProjectOfUser2DTOAssembler projectOfUser2DTOAssembler;

    public Response execute(ProjectOfUserAddCmd cmd) {
        ProjectOfUser projectOfUser = projectOfUser2DTOAssembler.toDomain(cmd.getProjectOfUserDTO());
        projectOfUser.create(UserThreadContext.get());
        projectOfUserRepository.create(projectOfUser);
        return Response.success();
    }
}
