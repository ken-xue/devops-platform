package io.kenxue.cicd.application.application.project.projectinfo.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectInfo2DTOAssembler implements Assembler<ProjectInfoDTO, ProjectInfo> {

    @Override
    public ProjectInfoDTO toDTO(ProjectInfo projectInfo) {
        return ProjectInfo2DTOMapStruct.INSTANCE.toDTO(projectInfo);
    }

    @Override
    public ProjectInfo toDomain(ProjectInfoDTO projectInfoDTO) {
        return ProjectInfo2DTOMapStruct.INSTANCE.toDomain(projectInfoDTO);
    }

    @Override
    public List<ProjectInfoDTO> toDTOList(List<ProjectInfo> projectInfoList) {
        return ProjectInfo2DTOMapStruct.INSTANCE.toDTOList(projectInfoList);
    }

    @Override
    public List<ProjectInfo> toDomainList(List<ProjectInfoDTO> projectInfoDTOList) {
        return ProjectInfo2DTOMapStruct.INSTANCE.toDomainList(projectInfoDTOList);
    }

    @Override
    public Page<ProjectInfoDTO> toDTOPage(Page<ProjectInfo> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), ProjectInfo2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
