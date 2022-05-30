package io.kenxue.devops.application.sys.user.command;

import io.kenxue.devops.application.sys.user.assembler.User2DTOAssembler;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.sys.UserRepository;
import io.kenxue.devops.domain.domain.sys.User;
import io.kenxue.devops.coreclient.dto.sys.user.UserAddCmd;
import io.kenxue.devops.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class UserAddCmdExe {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private User2DTOAssembler user2DTOAssembler;

    public Response execute(UserAddCmd cmd) {
        //检查账号是否已经存在
        User userId = userRepository.getByUserId(cmd.getUserDTO().getUserId());
        if(Objects.nonNull(userId)){
            return Response.error("账号已存在");
        }
        User user = user2DTOAssembler.toDomain(cmd.getUserDTO());
        user.create(UserThreadContext.get());

        if (Objects.isNull(user.getUserName()))user.setUserName(user.getUserId());
        userOfRoleRepository.save(cmd.getUserDTO().getRoleIdList(),user.getUuid());
        userRepository.create(user);
        return Response.success();
    }
}
