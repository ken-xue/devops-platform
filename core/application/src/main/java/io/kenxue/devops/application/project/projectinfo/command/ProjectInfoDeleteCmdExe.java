package io.kenxue.devops.application.project.projectinfo.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectInfoDeleteCmdExe {
    @Resource
    private ProjectInfoRepository projectInfoRepository;

    public Response execute(ProjectInfoDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            ProjectInfo projectInfo =projectInfoRepository.getById(id);
            projectInfo.deleted(UserThreadContext.get());
            projectInfoRepository.update(projectInfo);
        }
        return Response.success();
    }
}
