package io.kenxue.cicd.application.sys.user.command;

import io.kenxue.cicd.application.common.event.EventBus;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.user.event.UserDeleteEvent;
import io.kenxue.cicd.domain.repository.sys.UserRepository;
import io.kenxue.cicd.domain.domain.sys.User;
import io.kenxue.cicd.coreclient.dto.sys.user.UserDeleteCmd;
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
