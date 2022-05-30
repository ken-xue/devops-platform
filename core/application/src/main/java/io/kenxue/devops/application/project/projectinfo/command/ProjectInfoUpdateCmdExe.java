package io.kenxue.devops.application.project.projectinfo.command;

import io.kenxue.devops.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoUpdateCmd;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectInfoUpdateCmdExe {

    @Resource
    private ProjectInfoRepository projectInfoRepository;
    @Resource
    private ProjectInfo2DTOAssembler projectInfo2DTOAssembler;

    public Response execute(ProjectInfoUpdateCmd cmd) {
        ProjectInfo projectInfo = projectInfo2DTOAssembler.toDomain(cmd.getProjectInfoDTO());
        projectInfoRepository.update(projectInfo);
        return Response.success();
    }
}