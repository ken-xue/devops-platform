package io.kenxue.cicd.application.sys.user.command;

import io.kenxue.cicd.application.sys.user.assembler.User2DTOAssembler;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.sys.UserRepository;
import io.kenxue.cicd.domain.domain.sys.User;
import io.kenxue.cicd.coreclient.dto.sys.user.UserAddCmd;
import io.kenxue.cicd.domain.repository.sys.UserOfRoleRepository;
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
