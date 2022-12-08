package io.kenxue.devops.application.project.projectofuser.command;

import io.kenxue.devops.application.project.projectofuser.assembler.ProjectOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.ProjectOfUserRepository;
import io.kenxue.devops.domain.domain.project.ProjectOfUser;
import io.kenxue.devops.coreclient.dto.project.projectofuser.ProjectOfUserAddCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
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
