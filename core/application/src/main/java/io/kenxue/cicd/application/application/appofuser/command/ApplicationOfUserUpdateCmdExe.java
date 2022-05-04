package io.kenxue.cicd.application.application.appofuser.command;

import io.kenxue.cicd.application.application.appofuser.assembler.ApplicationOfUser2DTOAssembler;
import io.kenxue.cicd.domain.domain.application.ApplicationOfUser;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.ApplicationOfUserUpdateCmd;
import io.kenxue.cicd.domain.repository.application.ApplicationOfUserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationOfUserUpdateCmdExe {

    @Resource
    private ApplicationOfUserRepository applicationOfUserRepository;
    @Resource
    private ApplicationOfUser2DTOAssembler applicationOfUser2DTOAssembler;

    public Response execute(ApplicationOfUserUpdateCmd cmd) {
        ApplicationOfUser applicationOfUser = applicationOfUser2DTOAssembler.toDomain(cmd.getApplicationOfUserDTO());
        applicationOfUserRepository.update(applicationOfUser);
        return Response.success();
    }
}