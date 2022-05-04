package io.kenxue.cicd.application.project.projectinfo.command;

import io.kenxue.cicd.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoUpdateCmd;
import io.kenxue.cicd.domain.repository.project.ProjectInfoRepository;
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