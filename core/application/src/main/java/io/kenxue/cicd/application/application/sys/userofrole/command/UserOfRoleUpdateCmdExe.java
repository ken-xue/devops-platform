package io.kenxue.cicd.application.application.sys.userofrole.command;

import io.kenxue.cicd.application.application.sys.userofrole.assembler.UserOfRole2DTOAssembler;
import io.kenxue.cicd.domain.domain.sys.UserOfRole;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.userofrole.UserOfRoleUpdateCmd;
import io.kenxue.cicd.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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