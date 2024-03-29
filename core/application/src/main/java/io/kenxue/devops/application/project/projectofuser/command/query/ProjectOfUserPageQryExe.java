package io.kenxue.devops.application.project.projectofuser.command.query;

import io.kenxue.devops.application.project.projectofuser.assembler.ProjectOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.projectofuser.ProjectOfUserDTO;
import io.kenxue.devops.coreclient.dto.project.projectofuser.ProjectOfUserPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.project.ProjectOfUserRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.project.ProjectOfUser;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectOfUserPageQryExe {

    @Resource
    private ProjectOfUserRepository projectOfUserRepository;
    @Resource
    private ProjectOfUser2DTOAssembler projectOfUser2DTOAssembler;

    public PageResponse<ProjectOfUserDTO> execute(ProjectOfUserPageQry qry) {
        Page<ProjectOfUser> page = projectOfUserRepository.page(qry);
        return PageResponse.of(projectOfUser2DTOAssembler.toDTOPage(page));
    }
}
