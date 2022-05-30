package io.kenxue.devops.application.sys.user.command;

import io.kenxue.devops.application.common.event.EventBus;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.user.event.UserDeleteEvent;
import io.kenxue.devops.domain.repository.sys.UserRepository;
import io.kenxue.devops.domain.domain.sys.User;
import io.kenxue.devops.coreclient.dto.sys.user.UserDeleteCmd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 下午11:20
 */
@Component
public class UserDeleteCmdExe {
    @Resource
    private UserRepository userRepository;
    @Resource
    private EventBus eventBus;

    public Response execute(UserDeleteCmd cmd) {
        for(Long id:cmd.getIds()){
            User user = userRepository.getById(id);
            user.deleted(UserThreadContext.get());
            userRepository.update(user);
            //发布事件
            eventBus.publish(new UserDeleteEvent(user.getUuid()));
        }
        return Response.success();
    }
}
