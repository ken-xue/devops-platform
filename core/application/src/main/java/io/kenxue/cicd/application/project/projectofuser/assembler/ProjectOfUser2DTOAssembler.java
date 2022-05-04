package io.kenxue.cicd.application.project.projectofuser.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserDTO;
import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectOfUser2DTOAssembler implements Assembler<ProjectOfUserDTO, ProjectOfUser> {

    @Override
    public ProjectOfUserDTO toDTO(ProjectOfUser projectOfUser) {
        return ProjectOfUser2DTOMapStruct.INSTANCE.toDTO(projectOfUser);
    }

    @Override
    public ProjectOfUser toDomain(ProjectOfUserDTO projectOfUserDTO) {
        return ProjectOfUser2DTOMapStruct.INSTANCE.toDomain(projectOfUserDTO);
    }

    @Override
    public List<ProjectOfUserDTO> toDTOList(List<ProjectOfUser> projectOfUserList) {
        return ProjectOfUser2DTOMapStruct.INSTANCE.toDTOList(projectOfUserList);
    }

    @Override
    public List<ProjectOfUser> toDomainList(List<ProjectOfUserDTO> projectOfUserDTOList) {
        return ProjectOfUser2DTOMapStruct.INSTANCE.toDomainList(projectOfUserDTOList);
    }

    @Override
    public Page<ProjectOfUserDTO> toDTOPage(Page<ProjectOfUser> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), ProjectOfUser2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
