package io.kenxue.devops.application.project.projectinfo.command.query;

import io.kenxue.devops.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoGetQry;
import io.kenxue.devops.coreclient.dto.project.projectofuser.ProjectOfUserListQry;
import io.kenxue.devops.domain.domain.project.ProjectOfUser;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import io.kenxue.devops.domain.repository.project.ProjectOfUserRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectInfoGetQryExe {

    @Resource
    private ProjectInfoRepository projectInfoRepository;
    @Resource
    private ProjectInfo2DTOAssembler projectInfo2DTOAssembler;
    @Resource
    private ProjectOfUserRepository projectOfUserRepository;

    public SingleResponse<ProjectInfoDTO> execute(ProjectInfoGetQry qry) {
        ProjectInfoDTO projectInfoDTO = projectInfo2DTOAssembler.toDTO(projectInfoRepository.getById(qry.getId()));
        List<ProjectOfUser> userMembers = projectOfUserRepository.list(new ProjectOfUserListQry().setProjectUuid(projectInfoDTO.getUuid()));
        projectInfoDTO.setUserMembers(userMembers.stream().map(v->v.getUserUuid()).collect(Collectors.toList()));
        return SingleResponse.of(projectInfoDTO);
    }

}
