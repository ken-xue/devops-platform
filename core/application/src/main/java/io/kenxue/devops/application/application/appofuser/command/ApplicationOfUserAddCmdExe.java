package io.kenxue.devops.application.application.appofuser.command;

import io.kenxue.devops.application.application.appofuser.assembler.ApplicationOfUser2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.application.ApplicationOfUserRepository;
import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserAddCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.context.UserThreadContext;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationOfUserAddCmdExe {

    @Resource
    private ApplicationOfUserRepository applicationOfUserRepository;
    @Resource
    private ApplicationOfUser2DTOAssembler applicationOfUser2DTOAssembler;

    public Response execute(ApplicationOfUserAddCmd cmd) {
        ApplicationOfUser applicationOfUser = applicationOfUser2DTOAssembler.toDomain(cmd.getApplicationOfUserDTO());
        applicationOfUser.create(UserThreadContext.get());
        applicationOfUserRepository.create(applicationOfUser);
        return Response.success();
    }
}
