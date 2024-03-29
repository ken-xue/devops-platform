package io.kenxue.devops.application.project.projectinfo.command.query;

import io.kenxue.devops.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectInfoPageQryExe {

    @Resource
    private ProjectInfoRepository projectInfoRepository;
    @Resource
    private ProjectInfo2DTOAssembler projectInfo2DTOAssembler;

    public PageResponse<ProjectInfoDTO> execute(ProjectInfoPageQry qry) {
        Page<ProjectInfo> page = projectInfoRepository.page(qry);
        return PageResponse.of(projectInfo2DTOAssembler.toDTOPage(page));
    }
}
