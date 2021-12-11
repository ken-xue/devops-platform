package io.kenxue.cicd.application.application.sys.role.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.domain.domain.sys.Role;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.role.RoleDeleteCmd;
import io.kenxue.cicd.domain.repository.sys.RoleRepository;
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
