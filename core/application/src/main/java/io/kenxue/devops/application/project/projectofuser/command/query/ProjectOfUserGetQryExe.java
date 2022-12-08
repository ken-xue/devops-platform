package io.kenxue.devops.application.project.projectofuser.command.query;

import io.kenxue.devops.application.project.projectofuser.assembler.ProjectOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.projectofuser.ProjectOfUserDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.projectofuser.ProjectOfUserGetQry;
import io.kenxue.devops.domain.repository.project.ProjectOfUserRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectOfUserGetQryExe {

    @Resource
    private ProjectOfUserRepository projectOfUserRepository;
    @Resource
    private ProjectOfUser2DTOAssembler projectOfUser2DTOAssembler;

    public SingleResponse<ProjectOfUserDTO> execute(ProjectOfUserGetQry qry) {
        return SingleResponse.of(projectOfUser2DTOAssembler.toDTO(projectOfUserRepository.getById(qry.getId())));
    }

}
