package io.kenxue.devops.application.sys.userofrole.command;

import io.kenxue.devops.application.sys.userofrole.assembler.UserOfRole2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.userofrole.UserOfRoleAddCmd;
import io.kenxue.devops.domain.domain.sys.UserOfRole;
import io.kenxue.devops.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Component
public class UserOfRoleAddCmdExe {
    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private UserOfRole2DTOAssembler userOfRole2DTOAssembler;

    public Response execute(UserOfRoleAddCmd cmd) {
        UserOfRole userOfRole = userOfRole2DTOAssembler.toDomain(cmd.getUserOfRoleDTO());
        userOfRoleRepository.create(userOfRole);
        return Response.success();
    }
}
