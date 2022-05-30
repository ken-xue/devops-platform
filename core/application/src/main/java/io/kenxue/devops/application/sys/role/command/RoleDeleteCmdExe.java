package io.kenxue.devops.application.sys.role.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.domain.domain.sys.Role;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.role.RoleDeleteCmd;
import io.kenxue.devops.domain.repository.sys.RoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Stream;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Component
public class RoleDeleteCmdExe {
    @Resource
    private RoleRepository roleRepository;

    public Response execute(RoleDeleteCmd cmd) {
        Stream.of(cmd.getIds()).forEach(v->{
            Role role = roleRepository.getById(v);
            role.deleted(UserThreadContext.get());
            roleRepository.update(role);
        });
        return Response.success();
    }
}
