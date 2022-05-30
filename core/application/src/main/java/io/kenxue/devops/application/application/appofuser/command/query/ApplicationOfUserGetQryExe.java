package io.kenxue.devops.application.application.appofuser.command.query;

import io.kenxue.devops.application.application.appofuser.assembler.ApplicationOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserGetQry;
import io.kenxue.devops.domain.repository.application.ApplicationOfUserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationOfUserGetQryExe {

    @Resource
    private ApplicationOfUserRepository applicationOfUserRepository;
    @Resource
    private ApplicationOfUser2DTOAssembler applicationOfUser2DTOAssembler;

    public SingleResponse<ApplicationOfUserDTO> execute(ApplicationOfUserGetQry qry) {
        return SingleResponse.of(applicationOfUser2DTOAssembler.toDTO(applicationOfUserRepository.getById(qry.getId())));
    }

}
