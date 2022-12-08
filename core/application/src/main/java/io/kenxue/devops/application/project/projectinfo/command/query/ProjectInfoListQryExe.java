package io.kenxue.devops.application.project.projectinfo.command.query;

import io.kenxue.devops.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoListQry;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectInfoListQryExe {
    @Resource
    private ProjectInfoRepository projectInfoRepository;
    @Resource
    private ProjectInfo2DTOAssembler projectInfo2DTOAssembler;

    public MultiResponse<ProjectInfoDTO> execute(ProjectInfoListQry qry) {
        List<ProjectInfo> projectInfoDTOList = projectInfoRepository.list(qry);
        return MultiResponse.of(projectInfo2DTOAssembler.toDTOList(projectInfoDTOList));
    }
}
