package io.kenxue.cicd.application.application.project.projectofuser.command.query;

import io.kenxue.cicd.application.application.project.projectofuser.assembler.ProjectOfUser2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserGetQry;
import io.kenxue.cicd.domain.repository.project.ProjectOfUserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
