package io.kenxue.devops.application.sys.userofrole.command;

import io.kenxue.devops.application.sys.userofrole.assembler.UserOfRole2DTOAssembler;
import io.kenxue.devops.domain.domain.sys.UserOfRole;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.userofrole.UserOfRoleUpdateCmd;
import io.kenxue.devops.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Component
public class UserOfRoleUpdateCmdExe {

    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private UserOfRole2DTOAssembler userOfRole2DTOAssembler;

    public Response execute(UserOfRoleUpdateCmd cmd) {
        UserOfRole userOfRole = userOfRole2DTOAssembler.toDomain(cmd.getUserOfRoleDTO());
        userOfRoleRepository.update(userOfRole);
        return Response.success();
    }
}