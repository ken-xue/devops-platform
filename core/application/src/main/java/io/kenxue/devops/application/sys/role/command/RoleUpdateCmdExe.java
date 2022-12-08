package io.kenxue.devops.application.sys.role.command;

import io.kenxue.devops.application.sys.role.assembler.Role2DTOAssembler;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.domain.domain.sys.Role;
import io.kenxue.devops.domain.domain.sys.RoleOfMenu;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.role.RoleUpdateCmd;
import io.kenxue.devops.domain.factory.sys.RoleOfMenuFactory;
import io.kenxue.devops.domain.repository.sys.RoleRepository;
import io.kenxue.devops.domain.repository.sys.RoleOfMenuRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Component
public class RoleUpdateCmdExe {

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;
    @Resource
    private Role2DTOAssembler role2DTOAssembler;

    public Response execute(RoleUpdateCmd cmd) {
        Role role = role2DTOAssembler.toDomain(cmd.getRoleDTO());
        roleRepository.update(role);
        //更新关联表
        Role byId = roleRepository.getById(role.getId());
        roleOfMenuRepository.deleteByRoleUuid(byId.getUuid());
        cmd.getRoleDTO().getMenuList().forEach(v->{
            RoleOfMenu roleOfMenu = RoleOfMenuFactory.getRoleOfMenu();
            roleOfMenu.create(UserThreadContext.get(),byId.getUuid(),v);
            roleOfMenuRepository.create(roleOfMenu);
        });
        return Response.success();
    }
}