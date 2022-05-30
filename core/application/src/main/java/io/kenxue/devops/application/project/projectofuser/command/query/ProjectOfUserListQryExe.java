package io.kenxue.devops.application.project.projectofuser.command.query;

import io.kenxue.devops.application.project.projectofuser.assembler.ProjectOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.project.projectofuser.ProjectOfUserDTO;
import io.kenxue.devops.coreclient.dto.project.projectofuser.ProjectOfUserListQry;
import io.kenxue.devops.domain.domain.project.ProjectOfUser;
import io.kenxue.devops.domain.repository.project.ProjectOfUserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectOfUserListQryExe {
    @Resource
    private ProjectOfUserRepository projectOfUserRepository;
    @Resource
    private ProjectOfUser2DTOAssembler projectOfUser2DTOAssembler;

    public MultiResponse<ProjectOfUserDTO> execute(ProjectOfUserListQry qry) {
        List<ProjectOfUser> projectOfUserDTOList = projectOfUserRepository.list(qry);
        return MultiResponse.of(projectOfUser2DTOAssembler.toDTOList(projectOfUserDTOList));
    }
}
