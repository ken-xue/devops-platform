package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.ProjectInfoDO;
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
public interface ProjectInfo2DOMapStruct {

    ProjectInfo2DOMapStruct INSTANCE = Mappers.getMapper(ProjectInfo2DOMapStruct.class);

    @Mappings({})
    ProjectInfoDO toDO(ProjectInfo projectInfo);

    @Mappings({})
    ProjectInfo toDomain(ProjectInfoDO projectInfoDO);

    List<ProjectInfoDO> toDOList(List<ProjectInfo> projectInfoList);

    List<ProjectInfo> toDomainList(List<ProjectInfoDO> projectInfoDOList);
}
