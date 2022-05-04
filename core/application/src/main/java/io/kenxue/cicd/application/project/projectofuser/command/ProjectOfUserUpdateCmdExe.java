package io.kenxue.cicd.application.project.projectofuser.command;

import io.kenxue.cicd.application.project.projectofuser.assembler.ProjectOfUser2DTOAssembler;
import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserUpdateCmd;
import io.kenxue.cicd.domain.repository.project.ProjectOfUserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectOfUserUpdateCmdExe {

    @Resource
    private ProjectOfUserRepository projectOfUserRepository;
    @Resource
    private ProjectOfUser2DTOAssembler projectOfUser2DTOAssembler;

    public Response execute(ProjectOfUserUpdateCmd cmd) {
        ProjectOfUser projectOfUser = projectOfUser2DTOAssembler.toDomain(cmd.getProjectOfUserDTO());
        projectOfUserRepository.update(projectOfUser);
        return Response.success();
    }
}