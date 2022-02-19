package io.kenxue.cicd.application.application.project.projectinfo.assembler;

import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectInfo2DTOMapStruct {

    ProjectInfo2DTOMapStruct INSTANCE = Mappers.getMapper(ProjectInfo2DTOMapStruct.class);

    @Mappings({})
    ProjectInfoDTO toDTO(ProjectInfo projectInfo);

    @Mappings({})
    ProjectInfo toDomain(ProjectInfoDTO projectInfoDTO);

    List<ProjectInfoDTO> toDTOList(List<ProjectInfo> ProjectInfoList);

    List<ProjectInfo> toDomainList(List<ProjectInfoDTO> ProjectInfoDTOList);
}
