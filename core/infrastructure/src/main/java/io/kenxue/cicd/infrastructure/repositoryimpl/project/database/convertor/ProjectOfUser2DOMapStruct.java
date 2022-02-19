package io.kenxue.cicd.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.dataobject.ProjectOfUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectOfUser2DOMapStruct {

    ProjectOfUser2DOMapStruct INSTANCE = Mappers.getMapper(ProjectOfUser2DOMapStruct.class);

    @Mappings({})
    ProjectOfUserDO toDO(ProjectOfUser projectOfUser);

    @Mappings({})
    ProjectOfUser toDomain(ProjectOfUserDO projectOfUserDO);

    List<ProjectOfUserDO> toDOList(List<ProjectOfUser> projectOfUserList);

    List<ProjectOfUser> toDomainList(List<ProjectOfUserDO> projectOfUserDOList);
}
