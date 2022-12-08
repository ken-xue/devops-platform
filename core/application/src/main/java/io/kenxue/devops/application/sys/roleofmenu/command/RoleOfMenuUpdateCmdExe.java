package io.kenxue.devops.application.sys.roleofmenu.command;

import io.kenxue.devops.application.sys.roleofmenu.assembler.RoleOfMenu2DTOAssembler;
import io.kenxue.devops.domain.domain.sys.RoleOfMenu;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.roleofmenu.RoleOfMenuUpdateCmd;
import io.kenxue.devops.domain.repository.sys.RoleOfMenuRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Component
public class RoleOfMenuUpdateCmdExe {

    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;
    @Resource
    private RoleOfMenu2DTOAssembler roleOfMenu2DTOAssembler;

    public Response execute(RoleOfMenuUpdateCmd cmd) {
        RoleOfMenu roleOfMenu = roleOfMenu2DTOAssembler.toDomain(cmd.getRoleOfMenuDTO());
        roleOfMenuRepository.update(roleOfMenu);
        return Response.success();
    }
}