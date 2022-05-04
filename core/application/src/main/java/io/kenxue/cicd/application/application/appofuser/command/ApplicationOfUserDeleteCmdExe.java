package io.kenxue.cicd.application.application.appofuser.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.application.ApplicationOfUserRepository;
import io.kenxue.cicd.domain.domain.application.ApplicationOfUser;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.ApplicationOfUserDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
