package io.kenxue.devops.application.application.appofuser.command.query;

import io.kenxue.devops.application.application.appofuser.assembler.ApplicationOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserDTO;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserListQry;
import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import io.kenxue.devops.domain.repository.application.ApplicationOfUserRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationOfUserListQryExe {
    @Resource
    private ApplicationOfUserRepository applicationOfUserRepository;
    @Resource
    private ApplicationOfUser2DTOAssembler applicationOfUser2DTOAssembler;

    public MultiResponse<ApplicationOfUserDTO> execute(ApplicationOfUserListQry qry) {
        List<ApplicationOfUser> applicationOfUserDTOList = applicationOfUserRepository.list(qry);
        return MultiResponse.of(applicationOfUser2DTOAssembler.toDTOList(applicationOfUserDTOList));
    }
}
