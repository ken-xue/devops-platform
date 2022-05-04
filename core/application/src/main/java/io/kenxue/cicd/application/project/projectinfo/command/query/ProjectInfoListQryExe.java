package io.kenxue.cicd.application.project.projectinfo.command.query;

import io.kenxue.cicd.application.project.projectinfo.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoListQry;
import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import io.kenxue.cicd.domain.repository.project.ProjectInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
