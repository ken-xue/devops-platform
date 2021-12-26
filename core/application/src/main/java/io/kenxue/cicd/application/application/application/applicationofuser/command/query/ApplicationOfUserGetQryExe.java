package io.kenxue.cicd.application.application.application.applicationofuser.command.query;

import io.kenxue.cicd.application.application.application.applicationofuser.assembler.ApplicationOfUser2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.ApplicationOfUserDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.ApplicationOfUserGetQry;
import io.kenxue.cicd.domain.repository.application.ApplicationOfUserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 17:17:54
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
