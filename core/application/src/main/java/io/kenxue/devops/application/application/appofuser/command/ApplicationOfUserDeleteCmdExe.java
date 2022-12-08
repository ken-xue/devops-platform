package io.kenxue.devops.application.application.appofuser.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.application.ApplicationOfUserRepository;
import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationOfUserDeleteCmdExe {
    @Resource
    private ApplicationOfUserRepository applicationOfUserRepository;

    public Response execute(ApplicationOfUserDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            ApplicationOfUser applicationOfUser =applicationOfUserRepository.getById(id);
            applicationOfUser.deleted(UserThreadContext.get());
            applicationOfUserRepository.update(applicationOfUser);
        }
        return Response.success();
    }
}
