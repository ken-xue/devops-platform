package io.kenxue.devops.application.application.appofuser.command.query;

import io.kenxue.devops.application.application.appofuser.assembler.ApplicationOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserDTO;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.application.ApplicationOfUserRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationOfUserPageQryExe {

    @Resource
    private ApplicationOfUserRepository applicationOfUserRepository;
    @Resource
    private ApplicationOfUser2DTOAssembler applicationOfUser2DTOAssembler;

    public PageResponse<ApplicationOfUserDTO> execute(ApplicationOfUserPageQry qry) {
        Page<ApplicationOfUser> page = applicationOfUserRepository.page(qry);
        return PageResponse.of(applicationOfUser2DTOAssembler.toDTOPage(page));
    }
}
