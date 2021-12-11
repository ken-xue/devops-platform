package io.kenxue.cicd.application.application.sys.user.handler;

import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.coreclient.api.sys.UserOfRoleAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.user.event.UserDeleteEvent;
import io.kenxue.cicd.coreclient.dto.sys.userofrole.UserOfRoleDeleteCmd;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
@EventHandler
public class UserDeleteEventHandler implements EventHandlerI<Response, UserDeleteEvent> {

    @Resource
    private UserOfRoleAppService userOfRoleAppService;
    
    public Response execute(UserDeleteEvent event) {
        log.debug("Handling Event:{}",event);
        UserOfRoleDeleteCmd cmd = new UserOfRoleDeleteCmd();
        cmd.setUserUuids(new String[]{event.getUserUuid()});
        userOfRoleAppService.delete(cmd);
        return Response.success();
    }
}
