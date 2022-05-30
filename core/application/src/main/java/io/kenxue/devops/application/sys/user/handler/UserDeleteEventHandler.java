package io.kenxue.devops.application.sys.user.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.api.sys.UserOfRoleAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.user.event.UserDeleteEvent;
import io.kenxue.devops.coreclient.dto.sys.userofrole.UserOfRoleDeleteCmd;
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
