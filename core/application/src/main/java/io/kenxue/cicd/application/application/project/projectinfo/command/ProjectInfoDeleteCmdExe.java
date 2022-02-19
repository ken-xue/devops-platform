package io.kenxue.cicd.application.application.project.projectinfo.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.project.ProjectInfoRepository;
import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoDeleteCmd;
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
