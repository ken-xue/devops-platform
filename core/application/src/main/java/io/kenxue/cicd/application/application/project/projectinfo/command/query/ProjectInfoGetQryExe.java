package io.kenxue.cicd.application.application.project.projectinfo.command.query;

import io.kenxue.cicd.application.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoGetQry;
import io.kenxue.cicd.domain.repository.project.ProjectInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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

    public SingleResponse<ProjectInfoDTO> execute(ProjectInfoGetQry qry) {
        return SingleResponse.of(projectInfo2DTOAssembler.toDTO(projectInfoRepository.getById(qry.getId())));
    }

}
