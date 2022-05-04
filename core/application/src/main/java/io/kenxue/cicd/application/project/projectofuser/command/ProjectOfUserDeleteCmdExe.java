package io.kenxue.cicd.application.project.projectofuser.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.project.ProjectOfUserRepository;
import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectOfUserDeleteCmdExe {
    @Resource
    private ProjectOfUserRepository projectOfUserRepository;

    public Response execute(ProjectOfUserDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            ProjectOfUser projectOfUser =projectOfUserRepository.getById(id);
            projectOfUser.deleted(UserThreadContext.get());
            projectOfUserRepository.update(projectOfUser);
        }
        return Response.success();
    }
}
