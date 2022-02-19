package io.kenxue.cicd.application.application.project.projectofuser.assembler;

import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserDTO;
import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
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
public interface ProjectOfUser2DTOMapStruct {

    ProjectOfUser2DTOMapStruct INSTANCE = Mappers.getMapper(ProjectOfUser2DTOMapStruct.class);

    @Mappings({})
    ProjectOfUserDTO toDTO(ProjectOfUser projectOfUser);

    @Mappings({})
    ProjectOfUser toDomain(ProjectOfUserDTO projectOfUserDTO);

    List<ProjectOfUserDTO> toDTOList(List<ProjectOfUser> ProjectOfUserList);

    List<ProjectOfUser> toDomainList(List<ProjectOfUserDTO> ProjectOfUserDTOList);
}
